public class Utente{
		
		private String nome;
		private int tweet;
		
		public Utente(String nome, int tweet){
			this.nome = nome;
			this.tweet = tweet;
		}
		
		public String getNome() {
			return this.nome;
		}
		
		public int getTweet() {
			return this.tweet;
		}
		
		public void modify() {
			this.tweet++;
		}
	}