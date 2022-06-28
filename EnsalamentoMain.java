
public class EnsalamentoMain {
	// 201 401  - 101  - 101 301  - 401  - 
	// 201  - 101  - 301  - 401  - 
	// Turma 3  - Turma 4  - Turma 2  - Turma 1  - 
	// true  - true  - true  - true  -
	public static void main(String[] args) {
		Ensalamento e = new Ensalamento();
		Sala s1 = new Sala(1000, 101, 70, true);
		Sala s2 = new Sala(2000, 201, 100, false);
		Sala s3 = new Sala(3000, 301, 50, true);
		Sala s4 = new Sala(4000, 401, 122, false);
		e.addSala(s1);
		e.addSala(s2);
		e.addSala(s3);
		e.addSala(s4);
		Turma t1 = new Turma("Turma 1", "Prof. 1", 120, false);
		Turma t2 = new Turma("Turma 2", "Prof. 2", 40, true);
		Turma t3 = new Turma("Turma 3", "Prof. 3", 99, false);
		Turma t4 = new Turma("Turma 4", "Prof. 4", 70, true);
		t3.addHorario(26);
		t3.addHorario(27);
		t3.addHorario(35);
		e.addTurma(t3);
		t4.addHorario(19);
		t4.addHorario(24);
		t4.addHorario(31);
		e.addTurma(t4);
		t2.addHorario(5);
		t2.addHorario(10);
		t2.addHorario(32);
		e.addTurma(t2);
		t1.addHorario(2);
		t1.addHorario(16);
		t1.addHorario(30);
		e.addTurma(t1);
		e.alocarTodas();
		System.out.println(e.relatorioSalasPorTurma());
	}

}
