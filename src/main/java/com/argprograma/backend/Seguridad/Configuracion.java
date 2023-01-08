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

import com.argprograma.backend.Servicio.DetallesUsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class Configuracion implements WebMvcConfigurer {

    private final DetallesUsuarioServicio duServicio;
    private final JWTFiltroAutorizacion jwtFiltroAutorizacion;

    // Configuración de seguridad
    @Bean
    SecurityFilterChain cadenaFiltros(HttpSecurity seg, AuthenticationManager gestor) throws Exception {

        JWTFiltroAutenticacion jwtFiltroAutenticacion = new JWTFiltroAutenticacion();
        jwtFiltroAutenticacion.setAuthenticationManager(gestor);
        jwtFiltroAutenticacion.setFilterProcessesUrl("/login");

        return seg
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(jwtFiltroAutenticacion)
                .addFilterBefore(jwtFiltroAutorizacion, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager gestorAutenticacion(HttpSecurity seg) throws Exception {
        return seg.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(duServicio)
                .passwordEncoder(codificador())
                .and().build();
    }

    @Bean
    PasswordEncoder codificador() {
        return new BCryptPasswordEncoder();
    }
}
