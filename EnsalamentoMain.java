
public class EnsalamentoMain {

	public static void main(String[] args) {
		Sala s1 = new Sala(6, 101, 50, true);
		Sala s2 = new Sala(6, 101, 50, false);
		
		System.out.println(s1.getDescricao());
		System.out.println(s2.getDescricao());

	}

}
