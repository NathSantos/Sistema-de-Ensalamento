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
	
	public int TotalEspacoLivre() {
		int acm = 0;
	}
	
	public String relatorioResumoEnsalamento() {
		String relatorioEnsalamento = "Total de Salas: " + salas.size() +
				"\nTotal de Turmas: " + turmas.size() + "\nTurmas Alocadas: " +
				getTotalTurmasAlocadas() + "\nEspaços Livres: " + TotalEspacoLivre() + "\n";
		return relatorioEnsalamento;
	}
	
	public String relatorioTurmasPorSala() {
		Iterator<Sala> iteratorSala = salas.iterator();
		Iterator<TurmaEmSala> iteratorEnsalamento = ensalamento.iterator();
		String relatorio = relatorioResumoEnsalamento();
		
		while(iteratorSala.hasNext()) {
			relatorio += "\n--- " + iteratorSala.next().getDescricao() + " ---\n";
			
			while(iteratorEnsalamento.hasNext()) {
				
				if(iteratorEnsalamento.next().sala == iteratorSala.next()) {
					relatorio += "\n" + iteratorEnsalamento.next().turma.getDescricao() + "\n";
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
			relatorio += "\n" + iteratorTurma.next().getDescricao() + "\n";
			
			while(iteratorEnsalamento.hasNext()) {
				
				if((iteratorEnsalamento.next().turma == iteratorTurma.next()) && (iteratorEnsalamento.next().sala != null)) {
					relatorio += "Sala: " + iteratorEnsalamento.next().sala.getDescricao() + "\n";
				}
				else {
					if((iteratorEnsalamento.next().turma == iteratorTurma.next()) && (iteratorEnsalamento.next().sala == null)) {
						relatorio += "Sala: SEM SALA\n";
					}
				}
			}		
		}	
		return relatorio;
	}
}
