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

package com.argprograma.backend;

import com.argprograma.backend.Modelo.Contacto;
import com.argprograma.backend.Servicio.IContactoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacto")
public class ContactoControlador {
    @Autowired
    private IContactoServicio interContacto;

    @GetMapping("traer")
    public List<Contacto> traer(){
        return interContacto.traer();
    }
    
    @PostMapping("crear")
    public String crear(@RequestBody Contacto c){
        interContacto.guardar(c);
        return "Contacto guardado correctamente.";
    }
    
    @DeleteMapping("borrar/{id}")
    public String borrar(@PathVariable("id") Integer id){
        interContacto.borrar(id);
        return "Contacto borrado correctamente.";
    }
    
    @GetMapping("buscar/{id}")
    public Contacto buscar(@PathVariable("id") Integer id){
        return interContacto.buscar(id);
    }
    
    @PutMapping("editar/{id}")
    public Contacto editar(@PathVariable("id") Integer id,
            @RequestParam (name = "nombre", required = false) String nNombre,
            @RequestParam (name = "contacto", required = false) String nContacto){
        Contacto c = interContacto.buscar(id);
        if (nNombre != null) c.setNombre(nNombre);
        if (nContacto != null) c.setContacto(nContacto);
        
        interContacto.guardar(c);
        return c;
    }
}
