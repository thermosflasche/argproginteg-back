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

import com.argprograma.backend.Modelo.Estudio;
import com.argprograma.backend.Servicio.IEstudioServicio;
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
@RequestMapping("/estudio")
public class EstudioControlador {
    
    @Autowired
    private IEstudioServicio interEstudio;

    @GetMapping("traer")
    public List<Estudio> traer(){
        return interEstudio.traer();
    }
    
    @PostMapping("crear")
    public String crear(@RequestBody Estudio e){
        interEstudio.guardar(e);
        return "Estudio guardado correctamente.";
    }
    
    @DeleteMapping("borrar/{id}")
    public String borrar(@PathVariable("id") Integer id){
        interEstudio.borrar(id);
        return "Estudio borrado correctamente.";
    }
    
    @GetMapping("buscar/{id}")
    public Estudio buscar(@PathVariable("id") Integer id){
        return interEstudio.buscar(id);
    }
    
    @PutMapping("editar/{id}")
    public Estudio editar(@PathVariable("id") Integer id,
            @RequestParam (name = "titulo", required = false) String nTitulo,
            @RequestParam (name = "descripcion", required = false) String nDescripcion,
            @RequestParam (name = "anno", required = false) Integer nAnno,
            @RequestParam (name = "institucion", required = false) String nInstitucion){
        Estudio e = interEstudio.buscar(id);
        if (nTitulo != null) e.setTitulo(nTitulo);
        if (nDescripcion != null) e.setDescripcion(nDescripcion);
        if (nAnno != null) e.setAnno(nAnno);
        if (nInstitucion != null) e.setInstitucion(nInstitucion);
        
        interEstudio.guardar(e);
        return e;
    }
}
