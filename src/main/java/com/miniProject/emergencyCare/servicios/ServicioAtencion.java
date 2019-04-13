package com.miniProject.emergencyCare.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniProject.emergencyCare.excepciones.ExcepcionCantidadMedsNoDisponible;
import com.miniProject.emergencyCare.excepciones.ExcepcionMedicamentoNoRegistrado;
import com.miniProject.emergencyCare.excepciones.ExcepcionUsuarioInactivo;
import com.miniProject.emergencyCare.excepciones.ExcepcionUsuarioNoRegistrado;
import com.miniProject.emergencyCare.modelo.EmergencyAttention;
import com.miniProject.emergencyCare.modelo.State;
import com.miniProject.emergencyCare.modelo.Medicine;
import com.miniProject.emergencyCare.modelo.Patient;
import com.miniProject.emergencyCare.modelo.Supply;
import com.miniProject.emergencyCare.repositorios.AttentionRepository;
import com.miniProject.emergencyCare.repositorios.MedicineRepository;
import com.miniProject.emergencyCare.repositorios.PatientRepository;
import com.miniProject.emergencyCare.repositorios.SupplyRepository;

@Service
public class ServicioAtencion implements IServicioAtencion {

	@Autowired
	private PatientRepository repositorioPacientes;

	@Autowired
	private MedicineRepository repositorioMedicamentos;

	@Autowired
	private AttentionRepository repositorioAtenciones;

	@Autowired
	private SupplyRepository repositorioSuministros;

	@Override
	public void entregarMedicamento(Supply suministro) throws ExcepcionUsuarioInactivo,
			ExcepcionUsuarioNoRegistrado, ExcepcionCantidadMedsNoDisponible, ExcepcionMedicamentoNoRegistrado {

		verificarUsuario(suministro.getPaciente());
		verificarMedExista(suministro.getMedicamento());
		verificarCantMeds(suministro.getMedicamento(), suministro.getCantidad());
		repositorioSuministros.registrarSuministro(suministro);

	}

	private void verificarMedExista(Medicine medicamento) throws ExcepcionMedicamentoNoRegistrado {
		if (repositorioMedicamentos.getMedicamento(medicamento.getConsecutivo()) == null) {
			throw new ExcepcionMedicamentoNoRegistrado(medicamento.getNombreGenerico());
		}
	}

	private void verificarUsuario(Patient paciente) throws ExcepcionUsuarioInactivo, ExcepcionUsuarioNoRegistrado {
		if (repositorioPacientes.getPaciente(paciente.getDocumento()) == null) {
			throw new ExcepcionUsuarioNoRegistrado(paciente.getDocumento());
		} else if (paciente.getEstado().equals(State.Inactive)) {
			throw new ExcepcionUsuarioInactivo(paciente.getDocumento());
		}
	}

	// Se asume que en caso de que un medicamento no exista en la cantidad
	// suficiente se suministra toda la posible.
	private void verificarCantMeds(Medicine medicamento, int cantidadMeds) throws ExcepcionCantidadMedsNoDisponible {
		int cantidadAux = cantidadMeds;
		for (int i = 0; i < medicamento.getInventarioMedicamento().size() && cantidadAux != 0; i++) {
			if (medicamento.getInventarioMedicamento().get(i).getCantDisponible() > cantidadAux) {
				return;
			} else {
				cantidadAux -= medicamento.getInventarioMedicamento().get(i).getCantDisponible();
			}
		}
		if (cantidadAux != 0) {
			throw new ExcepcionCantidadMedsNoDisponible(medicamento.getNombreGenerico(), cantidadMeds);
		}
	}

	// Se asume que en caso de que un medicamento no exista en la cantidad
	// suficiente se suministra toda la posible.
	@Override
	public void actualizarMedsDisponibles(Supply suministro) {
		int cantidadAux = suministro.getCantidad();
		for (int i = 0; i < suministro.getMedicamento().getInventarioMedicamento().size() && cantidadAux != 0; i++) {
			if (suministro.getMedicamento().getInventarioMedicamento().get(i).getCantDisponible() > cantidadAux) {
				suministro.getMedicamento().getInventarioMedicamento().get(i).setCantDisponible(
						suministro.getMedicamento().getInventarioMedicamento().get(i).getCantDisponible()
								- cantidadAux);
			} else {
				cantidadAux -= suministro.getMedicamento().getInventarioMedicamento().get(i).getCantDisponible();
				suministro.getMedicamento().getInventarioMedicamento().remove(i);
				i--;
			}
		}
	}

	@Override
	public void atenderPaciente(EmergencyAttention atencionUrgencia) throws ExcepcionUsuarioInactivo,
			ExcepcionUsuarioNoRegistrado, ExcepcionCantidadMedsNoDisponible, ExcepcionMedicamentoNoRegistrado {
		for (int i = 0; i < atencionUrgencia.getMedicSuministrados().size(); i++) {
			entregarMedicamento(atencionUrgencia.getMedicSuministrados().get(i));
		}
		repositorioAtenciones.registrarAtencion(atencionUrgencia);
	}

}
