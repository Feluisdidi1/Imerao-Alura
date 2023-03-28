import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class api {

	public static void main(String[] args) throws Exception {

		// conexão http e buscar top 250 filmes
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		URI endereco = URI.create(url);
		var cliente = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
		String corpo = response.body();

		// filtrar dados a serem usados (titulo, poster, nota)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(corpo);

		// manipular e exibir dados

		for (Map<String, String> filme : listaDeFilmes) {
			double nota = Double.parseDouble(filme.get("imDbRating"));
			int estrelas = (int) nota;

			System.out.println("Titulo: " + " " + filme.get("fullTitle"));
			System.out.print("Ranking: "+" "+ filme.get("rank")+ " ");
			for (int i = 1; i <= estrelas; i++) {
				if (nota >= 6.5) {
					System.out.print("\u2B50");
				} else {
					System.out.print("🍅");
				}
			}
			System.out.println("\n");
		}
	}
}
