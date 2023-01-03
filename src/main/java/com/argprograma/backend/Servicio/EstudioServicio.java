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

import com.argprograma.backend.Modelo.Estudio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.argprograma.backend.Repositorio.EstudioRepositorio;
import org.springframework.data.domain.Sort;

@Service
public class EstudioServicio implements IEstudioServicio {
    @Autowired
    public EstudioRepositorio eRepo;

    @Override
    public List<Estudio> traer() {
        return eRepo.findAll(Sort.by(
                Sort.Direction.ASC, "anno"));
    }

    @Override
    public void guardar(Estudio t) {
        eRepo.save(t);
    }

    @Override
    public void borrar(Integer id) {
        eRepo.deleteById(id);
    }

    @Override
    public Estudio buscar(Integer id) {
        return eRepo.findById(id).orElse(null);
    }
    }
