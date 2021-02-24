package com.capgemini.model.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

		LocalDateTime start = pagamento.getDataPagamento().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0)
				.withNano(0);
		;
		LocalDateTime end = pagamento.getDataPagamento()
				.withDayOfMonth(pagamento.getDataPagamento().toLocalDate().lengthOfMonth()).withHour(23).withMinute(59)
				.withSecond(59).withNano(0);

		BigDecimal sumValor = this.repository.sumByDataPagamentoBetween(start, end);
		BigDecimal porcentagem = (sumValor.doubleValue() > 0) ? pagamento.getValor().divide(sumValor, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100.0D)) : new BigDecimal(0D);

		return porcentagem;
	}

}