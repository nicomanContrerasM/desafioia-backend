package ia.desafio.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ia.desafio.backend.model.Inscripcion;
import ia.desafio.backend.model.ResponseAPI;
import ia.desafio.backend.repository.DynamoDbRepository;

@RestController
@RequestMapping("/landing")
@CrossOrigin("*")
public class InscripcionController {

	@Autowired
	private DynamoDbRepository repository;
	
	private ResponseAPI responseapi;

	@PostMapping
	public ResponseEntity<?> insertIntoDynamoDB(@RequestBody Inscripcion obj) {
		responseapi = new ResponseAPI();
		
		if(repository.insertInscripcion(obj)) {
			responseapi.setMessage("operación realizada exitosamente");
			responseapi.setSuccess(true);
			
			return new ResponseEntity<ResponseAPI> (responseapi, HttpStatus.OK);
		
		}else {
			responseapi.setMessage("ha sucedido un error inesperado");
			responseapi.setSuccess(false);
			
			return new ResponseEntity<ResponseAPI> (responseapi, HttpStatus.OK);
		
		}
		
	}

	@GetMapping
	public ResponseEntity<?> getInscripcion(@RequestParam String id) {
		Inscripcion inscripcion = repository.getInscripcionById(id);
		return new ResponseEntity<Inscripcion>(inscripcion, HttpStatus.OK);
	}


}