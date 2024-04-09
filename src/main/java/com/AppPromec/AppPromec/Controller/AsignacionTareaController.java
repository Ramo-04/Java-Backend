package com.AppPromec.AppPromec.Controller;

import com.AppPromec.AppPromec.Entities.AsignacionTarea;
import com.AppPromec.AppPromec.Entities.Empleado;
import com.AppPromec.AppPromec.Entities.Servicio;
import com.AppPromec.AppPromec.Service.Imp.AsignacionTareaImp;
import com.AppPromec.AppPromec.Service.Imp.EmpleadoImp;
import com.AppPromec.AppPromec.Service.Imp.ServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/AsignacionTarea", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class AsignacionTareaController {

@Autowired
private AsignacionTareaImp asignacionTareaImp;

@Autowired
private EmpleadoImp empleadoImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create (@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            System.out.println("@@@@"+ request);

            AsignacionTarea asignacionTarea = new AsignacionTarea();

            asignacionTarea.setTipoTarea(request.get("tipo_tarea").toString());

            Empleado empleado = empleadoImp.findById(Long.parseLong(request.get("id_empleado").toString()));
            asignacionTarea.setEmpleado(empleado);



            this.asignacionTareaImp.create(asignacionTarea);

            response.put("status", "succes");

            response.put("data", "Registro exitoso");

        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);

            response.put("data",e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String, Object> response = new HashMap<>();

        try {
            List<AsignacionTarea> asignacionTareaList=this.asignacionTareaImp.findAll();

            response.put("status", "succes");

            response.put("data", asignacionTareaList);
        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);

            response.put("data",e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //UPDATE SERVICIO
    @GetMapping("/update/{id_asignacion_de_tareas}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id_asignacion_de_tareas, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            AsignacionTarea asignacionTarea = this.asignacionTareaImp.findById(id_asignacion_de_tareas);

            asignacionTarea.setTipoTarea(request.get("tipo_tarea").toString());


            response.put("status", "succes");

            response.put("data", asignacionTarea);

            this.asignacionTareaImp.update(asignacionTarea);
        } catch (Exception e) {

            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //DELETE SERVICIO
    @DeleteMapping("delete/{id_asignacion_de_tareas}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id_asignacion_de_tareas) {
        Map<String, Object> response = new HashMap<>();

        try {

            AsignacionTarea asignacionTarea = this.asignacionTareaImp.findById(id_asignacion_de_tareas);

            asignacionTareaImp.delete(asignacionTarea);

            response.put("status", "success");

            response.put("data", "Registro borrado correctamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);

            response.put("data", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
