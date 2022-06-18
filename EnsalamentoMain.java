
public class EnsalamentoMain {

	public static void main(String[] args) {
		Sala s1 = new Sala(6, 101, 50, true);
		Sala s2 = new Sala(6, 101, 50, false);
		Turma t1 = new Turma("Algoritmos e Estrutura de Dados I","Edleno Silva",60,true);
		
		t1.addHorario(1);
		t1.addHorario(15);
		t1.addHorario(29);
		
		System.out.println(t1.getDescricao());
		
		System.out.println(s1.getDescricao());
		System.out.println(s2.getDescricao());

	}

}
