package com.pais.ua.bus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;

import com.pais.ua.api.ApiOutResponse;

import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.request.KilometrajeRequest;

public interface ControlCombustibleBus {

	ApiOutResponse ListadoKiometraje(KilometrajeRequest objeto);
}
