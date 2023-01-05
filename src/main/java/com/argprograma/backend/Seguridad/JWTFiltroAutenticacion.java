/*    Backend - Argentina Programa 3.0: Backend con Spring+JPA+Hibernate
 *    Copyright (C) 2023  Thermosflasche
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>. */

package com.argprograma.backend.Seguridad;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTFiltroAutenticacion extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        CredAutenticacion credenciales = new CredAutenticacion();
        try {
            credenciales = new ObjectMapper().readValue(request.getReader(), CredAutenticacion.class);
        } catch (IOException e) {}
        
        UsernamePasswordAuthenticationToken PAT = new UsernamePasswordAuthenticationToken(
                credenciales.getEmail(), credenciales.getPassword(),
                Collections.emptyList());
        
        return getAuthenticationManager().authenticate(PAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        DetallesUsuario detalles = (DetallesUsuario) authResult.getPrincipal();
        String token = Token.crearToken(detalles.getNombre(), detalles.getUsername());
        
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        
        
        super.successfulAuthentication(request, response, chain, authResult);
    }
    
    
    
}
