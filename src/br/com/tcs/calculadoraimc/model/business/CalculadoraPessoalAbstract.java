package br.com.tcs.calculadoraimc.model.business;

import java.text.DecimalFormat;

public abstract class CalculadoraPessoalAbstract {
	protected static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	private int mlAgua;
	
	public CalculadoraPessoalAbstract() {
		this.mlAgua=2000;
	}

	protected int getMlAgua() {
		return mlAgua;
	}

	protected void setMlAgua(int mlAgua) {
		this.mlAgua = mlAgua;
	}
	
	public abstract String calcularImc(double altura, double peso);
	
	public String calcularAgua(double peso) {
		return "Beba " + this.mlAgua + " ml de água por dia";
	}
}
