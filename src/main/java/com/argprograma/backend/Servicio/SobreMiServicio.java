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

package com.argprograma.backend.Servicio;

import com.argprograma.backend.Modelo.SobreMi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.argprograma.backend.Repositorio.SobreMiRepositorio;

@Service
public class SobreMiServicio implements ISobreMiServicio {
    @Autowired
    public SobreMiRepositorio sRepo;

    @Override
    public List<SobreMi> traer() {
        return sRepo.findAll();
    }

    @Override
    public void guardar(SobreMi t) {
        sRepo.save(t);
    }

    @Override
    public void borrar(Integer id) {
        sRepo.deleteById(id);
    }

    @Override
    public SobreMi buscar(Integer id) {
        return sRepo.findById(id).orElse(null);
    }
    }
