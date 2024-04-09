package com.AppPromec.AppPromec.Controller;
import com.AppPromec.AppPromec.Entities.OrdenDeProduccion;
import com.AppPromec.AppPromec.Entities.Produccion;
import com.AppPromec.AppPromec.Service.Imp.OrdenDeProduccionImp;
import com.AppPromec.AppPromec.Service.Imp.ProduccionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/ordenDeProduccion", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class OrdenDeProduccionController {

    @Autowired
    private ProduccionImp produccionImp;

    @Autowired
    private OrdenDeProduccionImp ordenDeProduccionImp;


    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create (@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            System.out.println("@@@@"+ request);

            OrdenDeProduccion ordenDeProduccion = new OrdenDeProduccion();


            ordenDeProduccion.setNumero_de_produccion((String) request.get("numero_de_produccion"));


            Produccion produccion = produccionImp.findById(Long.parseLong(request.get("id_orden_de_produccion").toString()));
            ordenDeProduccion.setProduccion(produccion);



            this.ordenDeProduccionImp.create(ordenDeProduccion);

            response.put("status", "succes");

            response.put("data", "Registro exitoso");

        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);

            response.put("data",e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
    //FIND ALL PRODUCCION
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String, Object> response = new HashMap<>();

        try {
            List<OrdenDeProduccion> ordenDeProduccionList=this.ordenDeProduccionImp.findAll();
            response.put("status", "succes");

            response.put("data", ordenDeProduccionList);
        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);

            response.put("data",e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //UPDATE PRODUCCION
    @GetMapping("/update/{id_orden_de_produccion}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id_orden_de_produccion, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            OrdenDeProduccion ordenDeProduccion = this.ordenDeProduccionImp.findById(id_orden_de_produccion);

           ordenDeProduccion.setNumero_de_produccion((String) request.get("numero_de_produccion"));

            response.put("status", "succes");

            response.put("data", ordenDeProduccion);

            this.ordenDeProduccionImp.update(ordenDeProduccion);
        } catch (Exception e) {

            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //DELETE INVENTARIO
    @DeleteMapping("delete/{id_orden_de_produccion}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id_produccion) {
        Map<String, Object> response = new HashMap<>();

        try {

            Produccion produccion = this.produccionImp.findById(id_produccion);

            produccionImp.delete(produccion);

            response.put("status", "success");

            response.put("data", "Registro eliminado correctamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);

            response.put("data", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
