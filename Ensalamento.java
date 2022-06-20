import java.util.ArrayList;
import java.util.Iterator;

public class Ensalamento {
	ArrayList<Sala> salas = new ArrayList<Sala>();
	ArrayList<Turma> turmas = new ArrayList<Turma>();
	ArrayList<TurmaEmSala> ensalamento = new ArrayList<TurmaEmSala>();
	static int acumulador = 0;
	
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
			acumulador += sala.capacidade - turma.numAlunos;
			return true;
		}
		return false;
	}
	
	public void alocarTodas() {
		Iterator<Sala> iteratorSala = salas.iterator();
		Iterator<Turma> iteratorTurma = turmas.iterator();
		
		while(iteratorTurma.hasNext()) {	// vai alocar as turmas nas salas (um loop pra cada turma)
			ArrayList<Sala> salasRestantes = new ArrayList<Sala>();
			int numAlunosTurma = iteratorTurma.next().numAlunos;	// número de alunos da respectiva turma
			
			while(iteratorSala.hasNext()) {	// selecionando todas as salas que PODEM comportar tal turma (salasRestantes)
				if((numAlunosTurma <= iteratorSala.next().capacidade) && (iteratorTurma.next().acessivel == iteratorSala.next().acessivel) && (salaDisponivel(iteratorSala.next(), iteratorTurma.next().horarios) == true)) {
					salasRestantes.add(iteratorSala.next());
				}
			}
			
			Iterator<Sala> iteratorSalasRestante = salasRestantes.iterator();
			int menorCapacidade = 1000;
			Sala salaEscolhida = iteratorSalasRestante.next();
			
			while(iteratorSalasRestante.hasNext()) {	// dentre as salas que sobraram, qual a de menor capacidade (melhor sala para aquela turma)?
				if(iteratorSalasRestante.next().capacidade <= menorCapacidade) {
					menorCapacidade = iteratorSalasRestante.next().capacidade;
					salaEscolhida = iteratorSalasRestante.next();
				}
			}
			
			alocar(iteratorTurma.next(), salaEscolhida);
			
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
					relatorio += "Sala: SEM SALA\n";
				}
			}
			
			relatorio += "Sala: SEM SALA\n";
		}	
		return relatorio;
	}
}
