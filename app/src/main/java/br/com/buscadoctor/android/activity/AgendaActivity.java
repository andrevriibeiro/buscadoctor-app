package br.com.buscadoctor.android.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.adapter.HourAdapter;
import br.com.buscadoctor.android.enums.ConvenioEnum;
import br.com.buscadoctor.android.enums.OrigemEnum;
import br.com.buscadoctor.android.enums.StatusEnum;
import br.com.buscadoctor.android.manager.AgendaManager;
import br.com.buscadoctor.android.manager.ConsultaManager;
import br.com.buscadoctor.android.model.Consulta;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Convenio;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.model.Origem;
import br.com.buscadoctor.android.model.Paciente;
import br.com.buscadoctor.android.model.Status;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.model.dto.ConsultaDTO;
import br.com.buscadoctor.android.model.dto.HourDTO;
import br.com.buscadoctor.android.service.listener.AgendaServiceListener;
import br.com.buscadoctor.android.service.listener.ConsultaServiceListener;
import br.com.buscadoctor.android.util.Functions;
import br.com.buscadoctor.android.util.listener.RecyclerItemClickListener;

import static br.com.buscadoctor.android.service.SocketIoService.mSocket;

public class AgendaActivity extends AppCompatActivity {

    private List<HourDTO> hourDTOs;
    private HourDTO hourDTO;

    private Consultorio consultorio;
    private Especialista especialista;
    private Especialidade especialidade;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Calendar calendar = Calendar.getInstance();

        consultorio = getIntent().getParcelableExtra("consultorio");
        especialista = getIntent().getParcelableExtra("especialista");
        especialidade = getIntent().getParcelableExtra("especialidadeProcurada");

        Map<String, String> request = new HashMap<>();
        request.put("especialista", String.valueOf(especialista.getId()));
        request.put("consultorio", String.valueOf(consultorio.getId()));
        request.put("inicio", Functions.formatDate(calendar.getTime()));
        calendar.add(java.util.Calendar.MONTH, 1);
        request.put("fim", Functions.formatDate(calendar.getTime()));

        final RecyclerView recyclerViewHour = (RecyclerView) findViewById(R.id.recycler_view_hour);

        RecyclerView.LayoutManager layoutManagerHour = new LinearLayoutManager(this);

        recyclerViewHour.setLayoutManager(layoutManagerHour);

        MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);

        final RelativeLayout relativeLayoutMessage = (RelativeLayout) findViewById(R.id.relative_layout_message);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Map<String, String> request = new HashMap<>();
                request.put("especialista", String.valueOf(especialista.getId()));
                request.put("consultorio", String.valueOf(consultorio.getId()));
                request.put("inicio", Functions.getDateFormated(date.getDate()) + " 00:00:00");
                request.put("fim", Functions.getDateFormated(date.getDate()) + " 23:59:00");
                request.put("quantidade", "3");
                ConsultaManager consultaManager = new ConsultaManager(getApplicationContext());
                consultaManager.searchHorarios(request, new ConsultaServiceListener() {
                    @Override
                    public void onSuccess(Consulta consulta) {
                    }

                    @Override
                    public void onSuccess(List<HourDTO> hourDTOList) {
                        if (!hourDTOList.isEmpty()) {
                            hourDTOs = hourDTOList;
                            HourAdapter hourAdapter = new HourAdapter(hourDTOList);
                            recyclerViewHour.setAdapter(hourAdapter);
                            recyclerViewHour.setVisibility(View.VISIBLE);
                            relativeLayoutMessage.setVisibility(View.GONE);
                        } else {
                            recyclerViewHour.setVisibility(View.GONE);
                            relativeLayoutMessage.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFail(Throwable t) {
                    }
                });
            }
        });

        recyclerViewHour.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                hourDTO = hourDTOs.get(position);
                final String data = Functions.formatDate(hourDTO.getDiainicio());
                final String id = String.valueOf(hourDTO.getAgenda().getId());
                mSocket.emit("agendando", data, id, true);

                final List<Convenio> mConvenio = new ArrayList<>();

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater layoutInflater = getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.layout_confirmar_consulta, null, true);

                final Spinner spinnerConvenio = v.findViewById(R.id.spinner_convenio);

                TextView textViewEspecialista = v.findViewById(R.id.text_view_especialista);
                TextView textViewEspecialidade = v.findViewById(R.id.text_view_especialidade);
                TextView textViewConsultorio = v.findViewById(R.id.text_view_consultorio);
                TextView textViewHour = v.findViewById(R.id.text_view_hour);

                textViewEspecialista.setText(hourDTO.getEspecialista().getNome());
                textViewEspecialidade.setText(especialidade.getEspecialidade());
                textViewConsultorio.setText(hourDTO.getConsultorio().getNome());
                textViewHour.setText(Functions.formatDatePattern(hourDTO.getDiainicio()) + " as " + Functions.formatDatePattern(hourDTO.getDiafim()));
                builder.setView(v);

                builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConsultaDTO consultaDTO = new ConsultaDTO();
                        List<Consulta> consultas = new ArrayList<>();
                        Consulta consulta = new Consulta();

                        Status status = new Status();
                        status.setId(StatusEnum.PENDENTE_CONFIRMACAO.getID());
                        consulta.setStatus(status);

                        consulta.setAgenda(hourDTO.getAgenda());
                        consulta.setHoraInicio(hourDTO.getDiainicio());
                        consulta.setHoraFinal(hourDTO.getDiafim());
                        consulta.setNotificado(false);

                        consulta.setEspecialidade(especialidade);

                        Origem origem = new Origem();
                        origem.setId(OrigemEnum.ANDROID.getID());
                        consulta.setOrigem(origem);

                        Paciente paciente = new Paciente();
                        Usuario usuario = new Usuario();
                        usuario.setId(Functions.getUserId(getApplication()));
                        paciente.setUsuario(usuario);
                        paciente.setConsultorio(consultorio);
                        consultaDTO.setPaciente(paciente);

                        for (Convenio c : mConvenio) {
                            if (spinnerConvenio.getSelectedItem().toString().equals(c.getNome())) {
                                consulta.setConvenio(c);
                                consultaDTO.setConvenio(c);
                            }
                        }

                        consultas.add(consulta);
                        consultaDTO.setConsultas(consultas);

                        ConsultaManager consultaManager = new ConsultaManager(getApplicationContext());
                        consultaManager.createConsulta(consultaDTO, new ConsultaServiceListener() {
                            @Override
                            public void onSuccess(final Consulta consulta) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AgendaActivity.this);
                                builder.setTitle(getString(R.string.message_success));
                                builder.setMessage(getString(R.string.consulta_success));
                                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mSocket.emit("solicitaConsulta", String.valueOf(consulta.getAgenda().getEspecialista().getId()),
                                                Functions.getDateFormated(consulta.getHoraInicio()), Functions.formatCompleteHour(consulta.getHoraInicio()),
                                                String.valueOf(consulta.getId()), String.valueOf(consulta.getAgenda().getConsultorio().getId()), true);

                                        Map<String, String> request = new HashMap<>();
                                        request.put("especialista", String.valueOf(especialista.getId()));
                                        request.put("consultorio", String.valueOf(consultorio.getId()));

                                        request.put("inicio", Functions.getDateFormated(consulta.getHoraInicio()) + " 00:00:00");
                                        request.put("fim", Functions.getDateFormated(consulta.getHoraInicio()) + " 23:59:00");
                                        request.put("quantidade", "3");
                                        ConsultaManager consultaManager = new ConsultaManager(getApplicationContext());
                                        consultaManager.searchHorarios(request, new ConsultaServiceListener() {
                                            @Override
                                            public void onSuccess(Consulta consulta) {
                                            }

                                            @Override
                                            public void onSuccess(List<HourDTO> hourDTOList) {
                                                if (!hourDTOList.isEmpty()) {
                                                    hourDTOs = hourDTOList;
                                                    HourAdapter hourAdapter = new HourAdapter(hourDTOList);
                                                    recyclerViewHour.setAdapter(hourAdapter);
                                                    recyclerViewHour.setVisibility(View.VISIBLE);
                                                    relativeLayoutMessage.setVisibility(View.GONE);
                                                } else {
                                                    recyclerViewHour.setVisibility(View.GONE);
                                                    relativeLayoutMessage.setVisibility(View.VISIBLE);
                                                }
                                            }

                                            @Override
                                            public void onFail(Throwable t) {
                                            }
                                        });
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.show();
                            }

                            @Override
                            public void onSuccess(List<HourDTO> hourDTOList) {
                            }

                            @Override
                            public void onFail(Throwable t) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AgendaActivity.this);
                                builder.setTitle(getString(R.string.message_alert));
                                builder.setMessage(t.getMessage());
                                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mSocket.emit("agendandoCancel", data, id);
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.show();
                            }
                        });

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSocket.emit("agendandoCancel", data, id);
                        dialog.dismiss();
                    }
                });
                builder.show();

                List<String> list = new ArrayList<>();

                if (mConvenio.isEmpty()) {
                    Convenio c = new Convenio();
                    c.setId(ConvenioEnum.PARTICULAR.getID());
                    c.setNome("Particular");
                    mConvenio.add(c);
                }
                if (!mConvenio.isEmpty()) {
                    for (int i = 0; i < mConvenio.size(); i++) {
                        list.add(mConvenio.get(i).getNome());
                    }
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerConvenio.setAdapter(dataAdapter);
            }
        }));

        AgendaManager agendaManager = new AgendaManager(getApplicationContext());
        agendaManager.getNonWordays(request, new AgendaServiceListener() {
            @Override
            public void onSuccess(String string) {
                Log.d("", "");
            }

            @Override
            public void onFail(Throwable t) {
                Log.d("", "");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}