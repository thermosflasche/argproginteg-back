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

import com.argprograma.backend.Modelo.SobreMi;
import com.argprograma.backend.Servicio.ISobreMiServicio;
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
@RequestMapping("/sobremi")
public class SobreMiControlador {
    @Autowired
    private ISobreMiServicio interSobreMi;

    @GetMapping("traer")
    public List<SobreMi> traer(){
        return interSobreMi.traer();
    }
    
    @PostMapping("crear")
    public String crear(@RequestBody SobreMi s){
        interSobreMi.guardar(s);
        return "Registro SobreMi guardado correctamente.";
    }
    
    @DeleteMapping("borrar/{id}")
    public String borrar(@PathVariable Integer id){
        interSobreMi.borrar(id);
        return "Registro SobreMi borrado correctamente.";
    }
    
    @GetMapping("buscar/{id}")
    public SobreMi buscar(@PathVariable Integer id){
        return interSobreMi.buscar(id);
    }
    
    @PutMapping("editar/{id}")
    public SobreMi editar(@PathVariable Integer id,
            @RequestParam (name = "titulo", required = false) String nTitulo,
            @RequestParam (name = "info", required = false) String nInfo){
        SobreMi s = interSobreMi.buscar(id);
        if (nTitulo != null) s.setTitulo(nTitulo);
        if (nInfo != null) s.setInfo(nInfo);
        
        interSobreMi.guardar(s);
        return s;
    }
}
