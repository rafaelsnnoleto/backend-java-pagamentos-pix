package com.capgemini.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.capgemini.model.dto.PagamentoDTO;
import com.capgemini.model.entity.PagamentoEntity;
import com.capgemini.model.repository.PagamentoRepository;
import com.capgemini.utils.ResponseService;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	public ResponseService<PagamentoDTO> post(PagamentoEntity entity) {
		entity.setDataPagamento(LocalDateTime.now());
		PagamentoEntity pagamento = this.repository.save(entity);
		return new ResponseService<PagamentoDTO>(new PagamentoDTO(pagamento, this.getPorcentagemPagamento(pagamento)),
				HttpStatus.CREATED);
	}

	public ResponseService<List<PagamentoDTO>> getAll() {
		ResponseService<List<PagamentoDTO>> responseService = new ResponseService<List<PagamentoDTO>>();

		responseService.setData(this.repository.findAll().stream().map(pagamento -> {
			return new PagamentoDTO(pagamento, this.getPorcentagemPagamento(pagamento));
		}).collect(Collectors.toList()));

		return responseService;
	}

	private BigDecimal getPorcentagemPagamento(PagamentoEntity pagamento) {
		return new BigDecimal(0.0D);
	}

}