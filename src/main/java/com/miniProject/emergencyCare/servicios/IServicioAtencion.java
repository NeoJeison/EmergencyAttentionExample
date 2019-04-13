package com.miniProject.emergencyCare.servicios;

import com.miniProject.emergencyCare.excepciones.ExcepcionCantidadMedsNoDisponible;
import com.miniProject.emergencyCare.excepciones.ExcepcionMedicamentoNoRegistrado;
import com.miniProject.emergencyCare.excepciones.ExcepcionUsuarioInactivo;
import com.miniProject.emergencyCare.excepciones.ExcepcionUsuarioNoRegistrado;
import com.miniProject.emergencyCare.modelo.EmergencyAttention;
import com.miniProject.emergencyCare.modelo.Supply;

public interface IServicioAtencion {

	public void entregarMedicamento(Supply suministro) throws ExcepcionUsuarioInactivo, ExcepcionUsuarioNoRegistrado, ExcepcionCantidadMedsNoDisponible,
			ExcepcionMedicamentoNoRegistrado;

	public void actualizarMedsDisponibles(Supply suministro);

	public void atenderPaciente(EmergencyAttention atencionUrgencia) throws ExcepcionUsuarioInactivo, ExcepcionUsuarioNoRegistrado, ExcepcionCantidadMedsNoDisponible,
	ExcepcionMedicamentoNoRegistrado;

}
