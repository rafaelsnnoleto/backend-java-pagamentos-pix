package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.dto.PagamentoDTO;
import com.capgemini.model.entity.PagamentoEntity;
import com.capgemini.model.service.PagamentoService;
import com.capgemini.utils.ResponseService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService service;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<ResponseService<PagamentoDTO>> post(@RequestBody @Valid PagamentoEntity entity) {
		ResponseService<PagamentoDTO> responseService = this.service.post(entity);
		return ResponseEntity.status(responseService.getStatus()).body(responseService);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@Transactional
	public ResponseEntity<ResponseService<List<PagamentoDTO>>> getAll() {
		ResponseService<List<PagamentoDTO>> responseService = this.service.getAll();
		return ResponseEntity.status(responseService.getStatus()).body(responseService);
	}

}