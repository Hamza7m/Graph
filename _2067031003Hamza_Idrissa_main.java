package _20670310030Hamza_Idirssa;

import java.util.*;

public class _2067031003Hamza_Idrissa_main {
	static Map<String, List<String>> isimSehirMap;
	static Map<String, Integer> sehirNumaraMap;
	static Map<Integer, String> numaraSehirMap;
	static Map<String, Float> isimHizMap;
	static int[][] tablo;

	public static void main(String[] args) {

		_20670310030Hamza_Idirissa_ReadText readText = new _20670310030Hamza_Idirissa_ReadText();

		readText.setNameCityMap();
		readText.setCityNumberMap();
		readText.setNumberCityMap();
		readText.setGraphTable();
		readText.setNameSpeedMap();

		isimSehirMap = readText.getIsimSehirMap();
		sehirNumaraMap = readText.getSehirNumaraMap();
		numaraSehirMap = readText.getNumberSehirMap();
		isimHizMap = readText.getIsimHizMap();
		tablo = readText.getGraphTable();

		Scanner scanner = new Scanner(System.in);
       
		IlkSoru(scanner);
		IkinciSoru(scanner);
		UcuncuSoru(scanner);
		DorduncuSoru(scanner);
	}

	private static void IlkSoru(Scanner scanner) {
		System.out.println("Varış süresi hesaplanacak olan kişinin ismini giriniz:");

		String kullaniciGirdisi = scanner.nextLine();

		List<String> sehirler = isimSehirMap.get(kullaniciGirdisi);
		String sonSehir = sehirler.get(sehirler.size() - 1);

		List<Integer> rotaNumbers = new ArrayList<>();

		for (int i = 0; i < isimSehirMap.get(kullaniciGirdisi).size(); i++) {
			rotaNumbers.add(sehirNumaraMap.get(isimSehirMap.get(kullaniciGirdisi).get(i)) - 1);
		}

		float toplamYol = 0;
		for (int i = 0; i < rotaNumbers.size() - 1; i++) {
			toplamYol += tablo[rotaNumbers.get(i)][rotaNumbers.get(i + 1)];
		}

		int sure = (int) (toplamYol / isimHizMap.get(kullaniciGirdisi));

		System.out.println(kullaniciGirdisi + " " + sonSehir + " " + sure + " dk");
		System.out.println();
	}

	private static void IkinciSoru(Scanner scanner) {
		System.out.println("Toplam yol uzunluğu hesaplanacak olan kişinin ismini giriniz:");

		String kullaniciGirdisi2 = scanner.nextLine();

		List<Integer> rotaNumbers2 = new ArrayList<>();

		for (int i = 0; i < isimSehirMap.get(kullaniciGirdisi2).size(); i++) {
			rotaNumbers2.add(sehirNumaraMap.get(isimSehirMap.get(kullaniciGirdisi2).get(i)) - 1);
		}

		float toplamYol2 = 0;
		for (int i = 0; i < rotaNumbers2.size() - 1; i++) {
			toplamYol2 += tablo[rotaNumbers2.get(i)][rotaNumbers2.get(i + 1)];
		}

		System.out.println(kullaniciGirdisi2 + " toplam yol uzunluğu " + toplamYol2 + " km");
		System.out.println();
	}

	private static void UcuncuSoru(Scanner scanner) {
		System.out.println(
				"Kimin hangi saat itibariyle hangi noktada olduğunu hesaplamak için, aralarında bir boşluk bırakarak kişi ismini ve saati giriniz:");

		String kullaniciGirdisi3 = scanner.nextLine();
		String[] parcalar = kullaniciGirdisi3.split(" ");
		String isim = parcalar[0];
		String[] saatDakika = parcalar[1].split("\\.");
		int saat = Integer.parseInt(saatDakika[0]);
		int dakika = Integer.parseInt(saatDakika[1]);
		int baslangicSaati = 480;
		int istenenSaat = saat * 60 + dakika;
		int gidilenDakika = istenenSaat - baslangicSaati;

		if (saat < 8 || saat > 23) {
			System.out.println("İstenilen saat uygun değildir.");
			System.out.println();
			return;
		}

		List<Integer> rotaNumbers3 = new ArrayList<>();

		for (int i = 0; i < isimSehirMap.get(isim).size(); i++) {
			rotaNumbers3.add(sehirNumaraMap.get(isimSehirMap.get(isim).get(i)) - 1);
		}

		float toplamYol3 = 0;

		int ilkSehirNo = 1;
		int ikinciSehirNo = 1;

		for (int i = 0; i < rotaNumbers3.size() - 1; i++) {
			toplamYol3 += tablo[rotaNumbers3.get(i)][rotaNumbers3.get(i + 1)];
			if (toplamYol3 / isimHizMap.get(isim) >= gidilenDakika) {
				ilkSehirNo += rotaNumbers3.get(i);
				ikinciSehirNo += rotaNumbers3.get(i + 1);
				System.out.println(isim + " " + parcalar[1] + " itibariyle " + numaraSehirMap.get(ilkSehirNo) + "-"
						+ numaraSehirMap.get(ikinciSehirNo) + " arasındadır.");
				System.out.println();
				return;
			}
		}

		System.out.println("Hedefe varmıştır.");
	}

	private static void DorduncuSoru(Scanner scanner) {
		System.out.println(
				"İki şehir arasında direkt yol olup olmadığını sorgulamak için şehir isimlerini aralarında bir boşluk bırakarak giriniz");

		String kullaniciGirdisi4 = scanner.nextLine();
		String[] parcalar2 = kullaniciGirdisi4.split(" ");
		String ilkSehir = parcalar2[0];
		String ikinciSehir = parcalar2[1];

		if (tablo[sehirNumaraMap.get(ilkSehir) - 1][sehirNumaraMap.get(ikinciSehir) - 1] == 0) {
			System.out.println(numaraSehirMap.get(sehirNumaraMap.get(ilkSehir)) + " "
					+ numaraSehirMap.get(sehirNumaraMap.get(ikinciSehir)) + " arasında direkt yol yok.");
		}

		else {
			System.out.println(numaraSehirMap.get(sehirNumaraMap.get(ilkSehir)) + " "
					+ numaraSehirMap.get(sehirNumaraMap.get(ikinciSehir)) + " arasında direkt yol var.");
		}
		System.out.println();
	}

}
