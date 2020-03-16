package com.pais.ua.dao;

import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.request.KilometrajeRequest;

public interface ControlCombustibleDao {

	ApiOutResponse ListadoKiometraje(KilometrajeRequest objeto);
}
