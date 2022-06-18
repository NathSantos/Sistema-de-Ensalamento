import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Turma {
	String nome;
	String professor;
	int numAlunos;
	boolean acessivel;
	ArrayList<Integer> horarios = new ArrayList<Integer>();
	Hashtable<Integer, String> tabelaHorarios = new Hashtable<Integer, String>();
	int[] chavesHorarios = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
	String[] valoresHorarios = {"segunda 8hs","segunda 10hs","segunda 12hs","segunda 14hs","segunda 16hs","segunda 18hs","segunda 20hs",
								"terça 8hs","terça 10hs","terça 12hs","terça 14hs","terça 16hs","terça 18hs","terça 20hs",
								"quarta 8hs","quarta 10hs","quarta 12hs","quarta 14hs","quarta 16hs","quarta 18hs","quarta 20hs",
								"quinta 8hs","quinta 10hs","quinta 12hs","quinta 14hs","quinta 16hs","quinta 18hs","quinta 20hs",
								"sexta 8hs","sexta 10hs","sexta 12hs","sexta 14hs","sexta 16hs","sexta 18hs","sexta 20hs"};
	
	public void preencheTabelaHash() {
		for(int i=0;i<35;i++) {
			tabelaHorarios.put(chavesHorarios[i], valoresHorarios[i]);
		}
	}
	
	public Turma() {
	}
	
	public Turma (String nome, String professor, int numAlunos, boolean acessivel) {
		this.nome = nome;
		this.professor = professor;
		this.numAlunos = numAlunos;
		this.acessivel = acessivel;
	}
	
	public void addHorario(int horario) {
		horarios.add(horario);
	}
	
	public String getHorariosString() {
		Iterator<Integer> iterator = horarios.iterator();
		preencheTabelaHash();
		
		String horariosString = "";
		int i = 0;
		while(iterator.hasNext()) {
			if(i==horarios.size()-1) { // último horário
				horariosString += tabelaHorarios.get(iterator.next());
			} 
			else {
				horariosString += tabelaHorarios.get(iterator.next()) + ", ";
			}
			i++;
		}
		
		return horariosString;
	}
	
	public String getDescricao() {
		String descricaoTurma = "Turma: " + nome + "\nProfessor: " + professor + "\nNúmero de Alunos: " + numAlunos +
								 "\nHorário: " + getHorariosString() + "\nAcessível: ";
		if(acessivel == true) {
			descricaoTurma += "sim";
		}
		else {
			descricaoTurma += "não";
		}
		return descricaoTurma;
	}
}
