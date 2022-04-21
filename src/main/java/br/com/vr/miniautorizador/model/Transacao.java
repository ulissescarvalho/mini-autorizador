package br.com.vr.miniautorizador.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transacao implements Serializable{

	private static final long serialVersionUID = 5669781961294312972L;

}
