import java.util.ArrayList;
import java.util.Iterator;

public class Ensalamento {
	ArrayList<Sala> salas = new ArrayList<Sala>();
	ArrayList<Turma> turmas = new ArrayList<Turma>();
	ArrayList<TurmaEmSala> ensalamento = new ArrayList<TurmaEmSala>();
	
	public Ensalamento() {
	}
	
	public void addSala(Sala sala) {
		salas.add(sala);
	}
	
	public void addTurma(Turma turma) {
		turmas.add(turma);
	}
	
	public Sala getSala(Turma turma) {
		int i = 0;
		Iterator<TurmaEmSala> iterator = ensalamento.iterator();
		while(iterator.hasNext()) {
			if(turma == iterator.next().turma) {
				return ensalamento.get(i).sala;
			}
			i++;
		}
		return null;
	}
	
	public boolean salaDisponivel(Sala sala, int horario) {
		int i = 0;
		Iterator<TurmaEmSala> iterator = ensalamento.iterator();
		while(iterator.hasNext()) {
			if(sala == iterator.next().sala) {	
				
				Turma turmaAux = ensalamento.get(i).turma;
				Iterator<Integer> iteratorHorarios = turmaAux.horarios.iterator();
				
				while(iteratorHorarios.hasNext()) {
					if(horario == iteratorHorarios.next()) {
						return false;
					}	
				}
			}
			i++;
		}
		return true;
	}
	
	public boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios) {
		Iterator<Integer> iterator = horarios.iterator();
		while(iterator.hasNext()) {
			if(salaDisponivel(sala, iterator.next()) == false) {
				return false;
			}
		}
		return true;
	}
	
	public boolean alocar(Turma turma, Sala sala) {		
		if( (turma.acessivel == sala.acessivel) && (turma.numAlunos <= sala.capacidade) && (salaDisponivel(sala, turma.horarios) == true)) {
			TurmaEmSala novaTurma = new TurmaEmSala(turma, sala);
			ensalamento.add(novaTurma);
			return true;
		}
		return false;
	}
	
	public void alocarTodas() {
		Iterator<Turma> iteratorTurma = turmas.iterator();
		
		while(iteratorTurma.hasNext()) {	// vai alocar as turmas nas salas (um loop pra cada turma)
			Iterator<Sala> iteratorSala = salas.iterator();
			ArrayList<Sala> salasRestantes = new ArrayList<Sala>();
			Turma turmaAux = iteratorTurma.next();
			
			while(iteratorSala.hasNext()) {	// selecionando todas as salas que PODEM comportar tal turma (salasRestantes)
				Sala salaAux = iteratorSala.next();
				
				if((turmaAux.numAlunos <= salaAux.capacidade) && (turmaAux.acessivel == salaAux.acessivel) && (salaDisponivel(salaAux, turmaAux.horarios) == true)) {
					salasRestantes.add(salaAux);
				}
			}
			
			
			if(salasRestantes.size() > 0) {	// se tiver pelo menos 1 sala para a turma ser alocada....
				Iterator<Sala> iteratorSalasRestante = salasRestantes.iterator();
				int menorCapacidade = 1000;
				Sala salaEscolhida = null; 
				
				while(iteratorSalasRestante.hasNext()) {	// dentre as salas que sobraram, qual a de menor capacidade (melhor sala para aquela turma)?
					Sala salaRestante = iteratorSalasRestante.next();
					
					if(salaRestante.capacidade <= menorCapacidade) {
						menorCapacidade = salaRestante.capacidade;
						salaEscolhida = salaRestante;
					}
				}
				
				alocar(turmaAux, salaEscolhida);
				
			}
			
			for(int i=0;i<salasRestantes.size();i++) {
				salasRestantes.remove(i);
			}
		}
	}
	
	public int getTotalTurmasAlocadas() {
		int quant = 0;
		Iterator<TurmaEmSala> iterator = ensalamento.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().sala != null) {
				quant++;
			}
		}
		return quant;
	}
	
	public int getTotalEspacoLivre() {
		Iterator<TurmaEmSala> iterator = ensalamento.iterator();
		int acumulador = 0;
		
		while(iterator.hasNext()) {
			TurmaEmSala turmaSalaAux = iterator.next();
			acumulador += turmaSalaAux.sala.capacidade - turmaSalaAux.turma.numAlunos;
		}

		return acumulador;
	}
	
	public String relatorioResumoEnsalamento() {
		String relatorioEnsalamento = "Total de Salas: " + salas.size() +
				"\nTotal de Turmas: " + turmas.size() + "\nTurmas Alocadas: " +
				getTotalTurmasAlocadas() + "\nEspaços Livres: " + getTotalEspacoLivre() + "\n";
		return relatorioEnsalamento;
	}
	
	public String relatorioTurmasPorSala() {
		Iterator<Sala> iteratorSala = salas.iterator();
		Iterator<TurmaEmSala> iteratorEnsalamento = ensalamento.iterator();
		String relatorio = relatorioResumoEnsalamento();
		
		while(iteratorSala.hasNext()) {
			Sala salaAux = iteratorSala.next();
			relatorio += "\n--- " + salaAux.getDescricao() + " ---\n";

			while(iteratorEnsalamento.hasNext()) {
				TurmaEmSala turmaSalaAux = iteratorEnsalamento.next();
				if(turmaSalaAux.sala.sala == salaAux.sala) {
					relatorio += "\n" + turmaSalaAux.turma.getDescricao() + "\n";
				}
			}			
		}	
		return relatorio;
	}
	
	public String relatorioSalasPorTurma() {
		Iterator<Turma> iteratorTurma = turmas.iterator();
		Iterator<TurmaEmSala> iteratorEnsalamento = ensalamento.iterator();
		String relatorio = relatorioResumoEnsalamento();
		
		while(iteratorTurma.hasNext()) {
			Turma turmaAux = iteratorTurma.next();
			relatorio += "\n" + turmaAux.getDescricao() + "\n";
			
			if(iteratorEnsalamento.hasNext()) {
				while(iteratorEnsalamento.hasNext()) {	
					TurmaEmSala turmaSalaAux = iteratorEnsalamento.next();
					if((turmaSalaAux.turma == turmaAux) && (getSala(turmaSalaAux.turma) != null)) {
						relatorio += "Sala: " + turmaSalaAux.sala.getDescricao() + "\n";
					}
					else {
						relatorio += "Sala: SEM SALA\n";
					}
				}
			}
			else {
				relatorio += "Sala: SEM SALA\n";
			}
		}	
		return relatorio;
	}
}