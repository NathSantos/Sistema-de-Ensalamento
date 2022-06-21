
public class Sala {
	int bloco;
	int sala;
	int capacidade;
	boolean acessivel;
	
	public Sala() {
	}
	
	public Sala(int bloco, int sala, int capacidade, boolean acessivel) {
		this.bloco = bloco;
		this.sala = sala;
		this.capacidade = capacidade;
		this.acessivel = acessivel;
	}
	
	public String getDescricao() {
		String descricaoSala = "Bloco " + bloco + ", Sala " + sala + " (" + capacidade + " lugares, ";
		if(acessivel == true) {
			descricaoSala += "acessível)";
		}
		else {
			descricaoSala += "não acessível)";
		}
		return descricaoSala;
	}
}
