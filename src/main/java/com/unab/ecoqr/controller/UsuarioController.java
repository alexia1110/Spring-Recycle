package com.unab.ecoqr.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.unab.ecoqr.model.dao.IContenedorDao;
import com.unab.ecoqr.model.dao.IUsuarioDao;
import com.unab.ecoqr.model.entity.Contenedor;
import com.unab.ecoqr.model.entity.Residuo;
import com.unab.ecoqr.model.entity.Usuario;
import com.unab.ecoqr.services.IUsuarioService;
import com.unab.ecoqr.util.HeaderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*") 
public class UsuarioController {

    @Autowired
	private IUsuarioService usuarioService;

    @Autowired
	private IUsuarioDao usuarioDao;

    @Autowired
	private IContenedorDao contenedorDao;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("/new_user")
    public ResponseEntity<Usuario> createAlumno(@Validated @RequestBody Usuario usuarioDTO) throws URISyntaxException {
        log.info("REST request to save Usuario : {}", usuarioDTO);
       log.info("respuesta", usuarioDao.findByMailLikeIgnoreCase(usuarioDTO.getMail()));
       if (usuarioDao.findByMailLikeIgnoreCase(usuarioDTO.getMail()).isPresent()) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // BadRequestAlertException("Ya existe una actividad con el id", ENTITY_NAME, "idexists");
       }
       Usuario result = usuarioDao.save(usuarioDTO);
    log.info(result.toString());
        return ResponseEntity.created(new URI("/usuario/new_user/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Usuario", result.getMail()))
            .body(result);
    }

    @PostMapping("/new_container/{id}")
    public ResponseEntity<Contenedor> createContainer(@Validated @PathVariable Long id, @RequestBody Contenedor contenedorDTO) throws URISyntaxException {
        log.info("REST request to save Usuario : {}", id  );
        Optional<Usuario>  usuarioFind = usuarioService.findOne(id);
		if (!usuarioService.exist(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else{
    
             contenedorDTO.setUsuario(usuarioFind.get());
            Contenedor newContanedor = new Contenedor();
            newContanedor.setEstadoReciclado(contenedorDTO.isEstadoReciclado());
            newContanedor.setUsuario(usuarioFind.get());
            Contenedor result = contenedorDao.save(newContanedor);
            int lengt =  contenedorDTO.getResiduos().size();
            List<Residuo> newResid = new ArrayList<Residuo>();
    
            for(int i = 0; i < lengt; i++){
                Residuo resp = new Residuo();
                resp.setCategoria(contenedorDTO.getResiduos().get(i).getCategoria());
                resp.setMaterial(contenedorDTO.getResiduos().get(i).getMaterial());
                resp.setNombreEmpresa(contenedorDTO.getResiduos().get(i).getNombreEmpresa());
                resp.setContenedor(result);
              newResid.add(resp);
            }
             result.setResiduos(newResid);
             contenedorDao.save(result);
             return new ResponseEntity<>(HttpStatus.ACCEPTED);
            // return ResponseEntity.created(new URI("/usuario/new_container/"))
            // .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", "hi"))
            // .body(contenedorDTO);
           
           // log.info("respuesta", usuarioFind.getId().toString());
            // return  ResponseEntity.created(new URI("/usuario/new_container/ok"))
            //    .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", usuarioFind.toString()))
            //      .body(usuarioFind);
        }
       // log.info("respuesta", usuarioDao.findByMailLikeIgnoreCase(usuarioDTO.getMail()));
    //    if (usuarioDao.findByMailLikeIgnoreCase(usuario.getMail()).isPresent()) {
    //      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //         // BadRequestAlertException("Ya existe una actividad con el id", ENTITY_NAME, "idexists");
    //    }
 
  
    //    contenedorDTO.setUsuario(usuarioFind);
    // 
    //    log.info(result.toString());
     
    }
    
}
