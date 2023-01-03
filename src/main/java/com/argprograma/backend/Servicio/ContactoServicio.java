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

import com.argprograma.backend.Modelo.Contacto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.argprograma.backend.Repositorio.ContactoRepositorio;

@Service
public class ContactoServicio implements IContactoServicio {
    @Autowired
    public ContactoRepositorio cRepo;

    @Override
    public List<Contacto> traer() {
        return cRepo.findAll();
    }

    @Override
    public void guardar(Contacto t) {
        cRepo.save(t);
    }

    @Override
    public void borrar(Integer id) {
        cRepo.deleteById(id);
    }

    @Override
    public Contacto buscar(Integer id) {
        return cRepo.findById(id).orElse(null);
    }
    }
