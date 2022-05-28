package com.unab.ecoqr.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping("/login/mail={mail}&pass={pass}")
    public ResponseEntity<String> login(@Validated @PathVariable String mail,  @PathVariable String pass) throws URISyntaxException{
        Optional<Usuario>  usuarioFind = usuarioService.findByMail(mail);
        if (usuarioFind.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else{
            log.info(usuarioFind.get().getPass());
           // log.info(pass);
           if( !usuarioFind.get().getPass().equals(pass)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }

             return ResponseEntity.created(new URI("/usuario/login/ok"))
             .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", usuarioFind.get().getMail()))
            .body(usuarioFind.get().toString());
        }
    }

    @PostMapping("/new_container/{id}")
    public ResponseEntity<String> createContainer(@Validated @PathVariable Long id, @RequestBody Contenedor contenedorDTO) throws URISyntaxException {
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
             return ResponseEntity.created(new URI("/usuario/new_container/ok"))
             .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", usuarioFind.get().getMail()))
            .body(result.toString());
        }
     
    }

    @GetMapping("/delete_contenedor/{id}")
	public ResponseEntity<Contenedor>  eliminarContenedor(@Validated @PathVariable Long id) throws URISyntaxException {
		
		Contenedor contenedor = contenedorDao.findById(id).orElse(null);
		
		if(contenedor != null) {
			usuarioService.deleteContenedor(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	else{
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	}

    @GetMapping("/delete_usuario/{id}")
	public ResponseEntity<String>  eliminarUsuario(@Validated @PathVariable Long id) throws URISyntaxException {
		
		Usuario usuario = usuarioDao.findById(id).orElse(null);
		
		if(usuario != null) {
			usuarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	else{
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	}


    @GetMapping("/recycle/{id}")
    public ResponseEntity<Contenedor> reciclarContenedor( @PathVariable Long id)throws URISyntaxException {
        Contenedor contenedor = contenedorDao.findById(id).orElse(null);
        if(contenedor != null) {
			contenedor.setEstadoReciclado(true);
            contenedor.setFechaReciclado(new Date());
            contenedorDao.save(contenedor);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list_container/{id}")
    public  ResponseEntity<String>  listContenedor( @PathVariable Long id)throws URISyntaxException {

      Optional<Usuario>  usuarioFind = usuarioService.findOne(id);
		if (!usuarioService.exist(id)) {
            return null;
		}else{
            return ResponseEntity.created(new URI("/usuario/list_container/ok"))
            .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", usuarioFind.get().getMail()))
           .body(usuarioFind.get().getContenedores().toString());
        } 
    }

    @GetMapping("/list_container_reciclados/{id}")
    public  ResponseEntity<String>  listContenedorReciclados( @PathVariable Long id)throws URISyntaxException {

      Optional<Usuario>  usuarioFind = usuarioService.findOne(id);
		if (!usuarioService.exist(id)) {
            return null;
		}else{
            List<Contenedor>  contendores = usuarioService.findContenedorByEstado(id, true);
            return ResponseEntity.created(new URI("/usuario/list_container_reciclados/ok"))
            .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", usuarioFind.get().getMail()))
           .body(contendores.toString());
        } 
    }

        @GetMapping("/getResiduos/{id}")
        public ResponseEntity<String> listResiduo( @PathVariable Long id)throws URISyntaxException {
            Contenedor  cont = contenedorDao.findById(id).orElse(null);
            if(cont == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
            //  List<Contenedor> cont =  usuarioFind.get().getContenedores();
            List<Residuo> residuos = cont.getResiduos();
            for(int i = 0; i < residuos.size(); i++){
                log.info(residuos.get(i).toString());
            }
            log.info("residuos", residuos);
            //  Residuo residuo = cont.get(1).getResiduos().get(0);
          log.info(residuos.get(0).toString());
                return ResponseEntity.created(new URI("/usuario/getResiduos/ok"))
                .headers(HeaderUtil.createEntityCreationAlert("ecoQR", false, "Contenedor", "oka"))
               .body(residuos.toString());
            } 
    }
    
}
