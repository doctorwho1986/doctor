package com.doctor.lucenetutorial;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * AShortIntroductionToLucene 
 * @see http://oak.cs.ucla.edu/cs144/projects/lucene/
 * @author doctor
 *
 * @time   2015年1月14日 上午10:50:20
 */
public class AShortIntroductionToLucene {

	public static void main(String[] args) throws Throwable{
		String indexDir = AShortIntroductionToLucene.class.getClassLoader().getResource("lucene-tutorial/tempIndexDir").getFile();
		Indexer indexer = new Indexer(indexDir);
		indexer.indexHotels(HotelDatabase.gethHotels());
		indexer.commit();
		indexer.close();
		
		SearchEngine searchEngine = new SearchEngine(indexDir);
		TopDocs topDocs = searchEngine.serach("Notre Dame museum", 100);
		for (ScoreDoc doc : topDocs.scoreDocs) {
			Document document = searchEngine.getDocument(doc.doc);
			printDocument(document);
		}

	}
	
	private static void printDocument(Document document){
		String content = String.join("\t", document.get(LuceneField.id),document.get(LuceneField.name),document.get(LuceneField.city));
		System.out.println(content);
	}
	
	private static class Indexer implements Closeable {
		private static final Logger log = LoggerFactory.getLogger(Indexer.class);
		
		private IndexWriter indexWriter = null;

		public Indexer(String indexDir) throws IOException{
			if (indexWriter == null) {
				IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
				conf.setOpenMode(OpenMode.CREATE);
				Directory d = FSDirectory.open(new File(indexDir));
				indexWriter = new IndexWriter(d, conf);
			}
		}

		public void indexHotel(Hotel hotel) throws IOException{
			log.info("{index hotel:'{}'}",hotel);
			Document document = new Document();
			document.add(new StringField(LuceneField.id, hotel.getId(), Field.Store.YES));
			document.add(new StringField(LuceneField.name, hotel.getName(), Field.Store.YES));
			document.add(new StringField(LuceneField.city, hotel.getCity(), Field.Store.YES));
			document.add(new TextField(LuceneField.description, 
					String.join(",", hotel.getId(),hotel.getName(),hotel.getCity(),hotel.getDescription()), 
					Field.Store.NO));
			
			indexWriter.addDocument(document);
		}
		
		public void commit() throws IOException{
			indexWriter.commit();
		}
		
		public void indexHotels(Collection<Hotel> collection) throws IOException{
			for (Hotel hotel : collection) {
				indexHotel(hotel);
			}
		}
		
		@Override
		public void close() throws IOException {
			IOUtils.closeQuietly(indexWriter);
		}

	}

	private static class SearchEngine{

		private IndexSearcher indexSearcher;
		private QueryParser queryParser;

		public SearchEngine(String indexDir) throws IOException{
			indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(indexDir))));
			queryParser = new QueryParser(LuceneField.description, new StandardAnalyzer());
		}
		
		public TopDocs serach(String query,int n) throws IOException, ParseException{
			return indexSearcher.search(queryParser.parse(query), n);
		}
		
		public Document getDocument(int docId) throws IOException{
			return indexSearcher.doc(docId);
		}
	}
	
	private interface LuceneField{
		String id = "id";
		String name = "name";
		String city = "city";
		String description = "description";
	}
	private static class Hotel{
		private String id;
		private String name;
		private String city;
		private String description;
		
		public Hotel(){
			
		}
		
		public Hotel(String id,String name,String city,String description) {
			this.id = id;
			this.name = name;
			this.city = city;
			this.description = description;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}
	}
	
	private static class HotelDatabase{
		private static final List<Hotel> list = Arrays.asList(
				    new Hotel("1","Hôtel Rivoli","Paris","If you like historical Paris, you will adore the hotel. The hotel is right in the center of the city, right beside the Louvre, residence of the Kings of France during several centuries and, today, the greatest museum in the world."),
			        new Hotel("2","Hôtel Notre Dame","Paris","Right in the center of the Latin Quarter, in front of the Cathedral, the staff of Hotel Le Notre-Dame,will be delighted to welcome you during your stay in Paris."),
			        new Hotel("3","Hôtel Trinité","Paris","From the hotel you will find the Galeries Lafayette department store directly opposite, the Opera nearby and the Louvre Museum or Montmartre just a few minutes' stroll away"),
			        new Hotel("4","Hôtel Delavigne","Paris","Amongst all the picturesque attractions of Paris, there's one the whole world admires : the Latin Quarter."),
			        new Hotel("5","Hôtel Corail","Paris","Within walking distance from the Gare de Lyon as well as from the Opera-Bastille, the CORAIL HOTEL offers you a warm welcome and a comfortable stay."),
			        new Hotel("6","Hôtel Edouard VII","Paris","Elegant hotel located in the heart of Paris. It is only a few steps from Louvre Museum, Orsay Museum, Place Vendome and the most famous and prestigious parisian boutiques."),
			        new Hotel("7","Hôtel Carladez","Paris","This charming hotel is located on a lovely, typically Parisian square. Lively cafôs, restaurants and shops are plenty in this part of the 15th district."),
			        new Hotel("8","Hôtel Saint Germain","Paris","The Left Bank Hotel benefits from a wonderful location in the heart of the left bank area, which has always attracted artists and intellectuals."),
			        new Hotel("9","Hôtel Vincennes","Paris","Our traditional hotel is surrounded by the charms of Provence and the elegance of Paris. Chôteau and Bois de Vincennes, Floral Park, race course, Zoo, Disneyland Paris resort (via RER line A)."),
			        new Hotel("10","Hôtel Bretton","Paris","The Britannique is a genuine charming hotel. Its site, close to Place du chatelet, in the historical center of Paris is ideal for visiting by foot the oldest districts such as the Louvre, les Halles, le Marais, Saint Germain des Prôs and the Latin quarter, the islands of la Citô and Saint Louis ."),
			        new Hotel("11","Hôtel Cazaudehore","Paris","This spacious, embracing residence, at the edge of the forest and horseback riding paths, just a short walk of the castle where Louis XIV was born, is wrapped in the aroma of roses and the fragrance of the undergrowth, under the changing colours of the Ile de France's sky and the forest's flickering hues."),
			        new Hotel("12","Hôtel Horset","Paris","Ideally located right in the heart of Paris tourist area, between the Louvre and the Garnier Opera House, close to the Place Vendôme, and the Palais Royal, the recently renovated hotel l'Horset Opera is an oasis of calm in the heart of this bustling district of Paris."),
			        new Hotel("13","Hôtel Tonic","Paris","Located in the very heart of Paris, within a few steps from the Louvre, Notre Dame and the Champs Elysôes, Tonic Hotel Louvre proposes you its 34 rooms equipped with bathrooms, satellite television and minibar. "),
			        new Hotel("14","Hôtel Simone","Paris","A perfect location, in the heart of Paris elegant Right Bank : the Louvre, the Palais Royal, rue St Honorô and the Opôra are close by."),
			        new Hotel("15","Hôtel Odeon","Paris","In the heart of Old Saint-Germain, steps from fhe Odeon theatre, between the Luxembourg Gardens and Notre-Dame, a hotel teeming with charm in a street where quiet prevails: Grand Hôtel des Balcons."),
			        new Hotel("16","Hôtel Neuilly","Paris","A splendid small hotel with a special charm, refurbished in 2004 and situated in a fashionable part of the city close to the homes of many celebrities, 5 minutes from the Champs Elysôes and the Palais des Congrôs."),
			        new Hotel("17","Hôtel Chatillon","Paris","Hôtel Chôtillon Montparnasse is situated at the end of a small cul-de-sac, away from the hurly-burly, in the heart of a busy commercial area of the city. "),
			        new Hotel("18","Hôtel Bellevue","Paris"," We are located a few steps away from Notre-Dame and the Pantheon, in Saint Germain-des-Prôs quarter, in the heart of Paris. There are enough restaurants, cafôs terraces or fashion boutiques and people in the streets to make you appreciate the livingness of this area of the capital."),
			        new Hotel("19","Hôtel Marais","Paris","Situated at a focus point of the Paris business word and a few minutes walk from many attractive historical and cultural places (Marais, Notre Dame, Centre Pompidou and Louvre Museums...), the new hotel FRANCE EUROPE offers you quietness and a warm hospitality that will make you feel at home."),
			        new Hotel("20","Hôtel Opéra","Paris","Located in the center of  Paris, between Opera and Bourse, near the Louvre in the aera of tourism, shopping and business."),
			        new Hotel("21","Hôtel Beaugrenelle","Paris","The hotel is located on the left bank, close to the Eiffel Tower,the Champs de Mars, the River Seine and next to the Champs Elysôes, Montparnasse and the exhibition Center Porte de Versailles. Also next to the hotel, ôCap 15ô the business center, the Japanese house of culture and the Unescoô"),
			        new Hotel("22","Hôtel Italie","Paris"," ldeally located at the heart of the very chic Saint-Germain des Prôs district, the Madison is one of its most distinguished addresses. Discriminating travellers will appreciate its elegance, warm ambiance and attentive, courteous welcome."),
			        new Hotel("23","Hôtel Trocadero","Paris","Calm and quiet, is ideally located to visit Paris. While leaving your hotel you are on the splendid esplanade of Trocadôro which offers you the most beautiful prospect in town, you cross the Iôna bridge and you are already at the Eiffel Tower."),
			        new Hotel("24","Hôtel Royal Opéra","Paris","Situated in the heart of the business area, luxurious shops, theaters, hotel Royal Opera has a very good location Next to the Opera and the Madeleine Church, we offer you a lovely and warm atmosphere which will make you delighted."),
			        new Hotel("25","Hôtel Mermoz","Paris","The world of business meets the universe of fashion and art at the Hôtel Elysôes Mermoz, located between Champs Elysôes and Faubourg Saint Honorô with its luxury boutiques. The 22 rooms and 5 suites offer up to date amenities. "),
			        new Hotel("26","Albert 1er","Nice","An elegant Riviera-style building facing the sea and floral gardens, the Hotel Albert ler is located in the very heart of Nice, a short walk away from ‘La Vieille Ville’ (Old Town)."),
			        new Hotel("27","Nice Fleurs","Nice","The three-star residence Nice Fleurs enjoys an ideal location in the centre of Nice, neighbouring the beach and the famous Promenade des Anglais."),
			        new Hotel("28","Hotel Acropole Nice","Nice","The Hotel Acropole is situated in the heart of Nice, close to the beautiful beaches, the Acropole Conference Center and the shopping area."),
			        new Hotel("29","Brice Hotel","Nice","A hotel with restaurant of charm in a single Oasis of greenery to the heart of Nice. The Brice hotel guarantees to you a pleasant stay with its cordial garden and its warm reception."),
			        new Hotel("30","Hôtel Excelsior","Nice","The Excelsior Hotel, constructed in 1898, with a tradition and comfort 'Belle Epoque', offers you the very warmest welcome, with the casual atmosphere of luxury."),
			        new Hotel("31","Le Pigonnet Hotel","Aix-en-Provenve","Located close to the town center, a 18th century Provencal country house surrounded by a magnificent park from which Cezanne painted the Mount Saint Victoire."),
			        new Hotel("32","Le Chateau de la Pioline Hotel","Aix-en-Provenve","Magnificent hotel in a beautiful fortified chateau from 16th century by a magnificent park."),
			        new Hotel("33","Royal Mirabeau Hotel","Aix-en-Provenve","Elegant hotel, surrounded by a beautiful garden and green areas, offering cheerful and comfortable rooms with a view of the pinewood or golf course and St. Victoire mountain."),
			        new Hotel("34","Aquabella Hotel","Aix-en-Provenve","Comfortable hotel with a wide choice of relaxation facilities, ideally located in the heart of the old town, at the foot of the ancient fortifications."),
			        new Hotel("35","Mona Lisa Fuveau Hotel","Aix-en-Provenve","Located in Fuveau, the Mona Lisa hotel offers peace and quiet of the Provencal countryside, high-tech equipment, and top-of-the-range service."),
			        new Hotel("36","Hotellerie Les Frenes Hotel","Avignon","Luxury family run hotel in a superb XIX century residence, surrounded by a hundred years old garden, set close to the Pope's Palace and the Avignon's Bridge."),
			        new Hotel("37","Les Agassins Hotel","Avignon","Comfortable and friendly hotel decorated with lovely collection of paintings, offering splendid garden and the best restaurant in the region."),
			        new Hotel("38","Clarion Cloitre Saint Louis Hotel","Avignon","Elegant hotel set in a historic stone cloister, boasting rooms with majestic decorations, overlooking tree-filled garden, or the rooftops, monuments and Museum of Avignon, located close to the city center."),
			        new Hotel("39","Auberge de Cassagne Hotel","Avignon","Situated on the edge of Avignon, hidden in an oasis of green gardens and refreshing fountains, the Auberge de Cassagne, of a refined architectural style, is housed in a former bastide dating from 1850."),
			        new Hotel("40"," Grand Hotel","Avignon"," New hotel complex, offering comfortable, elegant and spacious suites and apartments, facing the old city ramparts, just steps away from the center of Avignon.")
				);
		
		public static List<Hotel> gethHotels(){
			return list;
		}
		
		public static Hotel getHotelById(String id){
			List<Hotel> collect = list.stream().filter(p -> p.id.equals(id)).collect(Collectors.toList());
			return collect.isEmpty()?null:collect.get(0);
		}
	}
}
