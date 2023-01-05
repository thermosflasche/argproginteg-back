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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Token {
    private final static String SECRETO = "selbstverständlichKrankenversicherung";
    private final static Long VALIDEZ_MSEC = 86_400_000L;
    
    public static String crearToken(String nombre, String email){
        Date expiracion = new Date(System.currentTimeMillis() + VALIDEZ_MSEC);
        
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiracion)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(SECRETO.getBytes()))
                .compact();
    }
    
    public static UsernamePasswordAuthenticationToken recibirAutenticacion(String token){
        try{
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRETO.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        
        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }

}
