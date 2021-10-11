package com.krugercorp.ec.vacunacion.controller;

import com.krugercorp.ec.vacunacion.pojos.ActualizaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.RegistrarEmpleadoPojo;
import com.krugercorp.ec.vacunacion.services.EmpleadoCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("empleado/")
public class EmpleadoController {

    @Autowired
    private EmpleadoCoreService empleadoCore;

    @PostMapping("registro/")
    @ResponseStatus(HttpStatus.OK)
    public void registraEmpleado(@RequestBody RegistrarEmpleadoPojo empleadoNuevo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println("entrada" +empleadoNuevo);
        empleadoCore.registroEmpleado(empleadoNuevo);
    }

//    @PostMapping("asignar_rol")
//    @ResponseStatus(HttpStatus.OK)
//    public void login(@RequestBody AsignaRolPojo asignar) {
//        loginCore.asignaRol(asignar);
//    }

    @PutMapping("actualizar/")
    @ResponseStatus(HttpStatus.OK)
    public void actializaEmpleado(@RequestBody ActualizaEmpleadoPojo actualizaEmpleado){
        empleadoCore.actualizaEmpleado(actualizaEmpleado);
    }

//    @GetMapping(value = "/reimprime/{numFactura}", produces = MediaType.APPLICATION_PDF_VALUE)
//    public InputStreamResource reimprimirFactura(@PathVariable("numFactura") String numeroFactura) {
//        System.out.println("Numero Factura "+numeroFactura);
//        return new InputStreamResource(iPagoService.reimprimeFactura(numeroFactura));
//    }
//
//    @GetMapping("/carrito_m03_pagar5")
//    public ResponseEntity<InputStreamResource> pagoTramites5(Authentication authentication)
//            throws MalformedURLException {
//        ResponseEntity bytes;
//        bytes = null;
//        try {
//            bytes = iRestService.createPost(authentication);
//        } catch (IOException e) {
//            System.out.println("cccccc");
//            e.printStackTrace();
//        }
//        return bytes;
//    }
//
//    @DeleteMapping("carrito_m03/{id}")
//    public ResponseEntity<?> Deletecarrito(@PathVariable("id") Long formM03Id) {
//        Map<String, Object> response = new HashMap<>();
//        iPagoService.EliminarItem(formM03Id);
//        response.put("respuesta", "ok");
//        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//    }

}
