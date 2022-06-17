package com.unab.ecoqr.controller;

import com.unab.ecoqr.services.InfoService;
import com.unab.ecoqr.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.unab.ecoqr.model.dao.IInfo;
import com.unab.ecoqr.model.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
@CrossOrigin(origins = "*")
public class InfoController {
   
    @Autowired
    private InfoService infoService;
    @Autowired
    private  IInfo infoDao;
   

    @GetMapping("/list_info/{categoria}")
    public ResponseEntity<String> listContenedor(@PathVariable String categoria) throws URISyntaxException {

        List<Info> usuarioFind = infoService.findByCategoria(categoria);
        if (usuarioFind.isEmpty()) {
            return null;
        } else {
            return ResponseEntity.created(new URI("/info/categoria/ok"))
                    .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor","ok"))
                    .body(usuarioFind.toString());
        }
    }

    @PostMapping("/new_info")
    public ResponseEntity<Info> createAlumno(@Validated @RequestBody Info infoDTO) throws URISyntaxException {

        Info result = infoDao.save(infoDTO);
        return ResponseEntity.created(new URI("/usuario/new_user/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Usuario", "ok"))
                .body(result);
    }

}
