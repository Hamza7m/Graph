package _20670310030Hamza_Idirssa;

import java.io.*;
import java.util.*;

public class _20670310030Hamza_Idirissa_ReadText {
	private String dosyaYolu = "Text.txt"; 
																																			// dosyanın
	private String kisilerinSeyahatNoktalariVeHizlariCumlesi = "Kişilerin Seyahat Noktaları ve Hızları:";

	private String sehirlerVeKodlariCumlesi = "Şehirler ve Kodları:";

	private String kisilerVeBaslangicNoktalariCumlesi = "Kişiler ve Başlangıç Noktaları:";
	private String grafCumlesi = "Graf:";

	private Map<String, List<String>> isimSehirMap = new HashMap<>();
	private Map<String, Integer> sehirNumaraMap = new HashMap<>();

	private Map<Integer, String> numaraSehirMap = new HashMap<>();
	private Map<String, Float> isimHizMap = new HashMap<>();
	private int[][] grafTablosu;
	private int row = 0;
	private int column = 0;
	private List<Integer> tabloDegerleri = new ArrayList<>();

	public void setNameCityMap() {
		try {
			FileReader dosyaOkuyucu = new FileReader(dosyaYolu);
			BufferedReader bufferedReader = new BufferedReader(dosyaOkuyucu);
			String satir;
			boolean kisilerinSeyahatNoktalariVeHizlariCumlesiBulundu = false;

			while ((satir = bufferedReader.readLine()) != null) {
				if (kisilerinSeyahatNoktalariVeHizlariCumlesiBulundu) {
					String[] satirParcalar = satir.split(" ");
					String[] sehirler = satirParcalar[1].split("-");
					List<String> sehirListesi = new ArrayList<>();
					for (int i = 0; i < sehirler.length; i++) {
						sehirListesi.add(sehirler[i]);
					}

					isimSehirMap.put(satirParcalar[0], sehirListesi);

				} else if (satir.startsWith(kisilerinSeyahatNoktalariVeHizlariCumlesi)) {
					kisilerinSeyahatNoktalariVeHizlariCumlesiBulundu = true;
				}

			}

			dosyaOkuyucu.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCityNumberMap() {
		try {
			FileReader dosyaOkuyucu = new FileReader(dosyaYolu);
			BufferedReader bufferedReader = new BufferedReader(dosyaOkuyucu);
			String satir;
			boolean grafCumlesiBulundu = false;

			while ((satir = bufferedReader.readLine()) != null) {
				if (satir.startsWith(grafCumlesi)) {
					break; 
				}

				if (grafCumlesiBulundu) {
					String[] satirParcalar = satir.split(" ");
					int numara = Integer.parseInt(satirParcalar[1]);
					sehirNumaraMap.put(satirParcalar[0], numara); 

				} else if (satir.startsWith(sehirlerVeKodlariCumlesi)) {
					grafCumlesiBulundu = true;
				}

			}

			dosyaOkuyucu.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNumberCityMap() {
		try {
			FileReader dosyaOkuyucu = new FileReader(dosyaYolu);
			BufferedReader bufferedReader = new BufferedReader(dosyaOkuyucu);
			String satir;
			boolean grafCumlesiBulundu = false;

			while ((satir = bufferedReader.readLine()) != null) {
				if (satir.startsWith(grafCumlesi)) {
					break; 
				}

				if (grafCumlesiBulundu) {
					String[] satirParcalar = satir.split(" ");
					int numara = Integer.parseInt(satirParcalar[1]);
					numaraSehirMap.put(numara, satirParcalar[0]);

				} else if (satir.startsWith(sehirlerVeKodlariCumlesi)) {
					grafCumlesiBulundu = true;
				}

			}

			dosyaOkuyucu.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNameSpeedMap() {
		try {
			FileReader dosyaOkuyucu = new FileReader(dosyaYolu);
			BufferedReader bufferedReader = new BufferedReader(dosyaOkuyucu);
			String satir;
			boolean kisilerinSeyahatNoktalariVeHizlariCumlesiBulundu = false;
			while ((satir = bufferedReader.readLine()) != null) {

				if (kisilerinSeyahatNoktalariVeHizlariCumlesiBulundu) {
					String[] satirParcalar = satir.split(" ");
					float speed = Float.parseFloat(satirParcalar[satirParcalar.length - 2]);

					isimHizMap.put(satirParcalar[0], speed);

				} else if (satir.startsWith(kisilerinSeyahatNoktalariVeHizlariCumlesi)) {
					kisilerinSeyahatNoktalariVeHizlariCumlesiBulundu = true;
				}

			}

			dosyaOkuyucu.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setGraphTable() {
		try {
			FileReader dosyaOkuyucu = new FileReader(dosyaYolu);
			BufferedReader bufferedReader = new BufferedReader(dosyaOkuyucu);
			String satir;
			boolean grafCumlesiBulundu = false;

			while ((satir = bufferedReader.readLine()) != null) {

				if (satir.startsWith(kisilerVeBaslangicNoktalariCumlesi)) {
					grafTablosu = new int[row][column];
					int listIndex = 0;
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < column; j++) {
							grafTablosu[i][j] = tabloDegerleri.get(listIndex);
							listIndex++;
						}
					}
					break; 
				}

				if (grafCumlesiBulundu) {
					row++;
					String[] parcalar = satir.split("	");
					for (int i = 1; i < parcalar.length; i++) {
						int numara = Integer.parseInt(parcalar[i]);
						tabloDegerleri.add(numara);
					}
					column = parcalar.length - 1;
				} else if (satir.startsWith("	")) {
					grafCumlesiBulundu = true;
				}

			}

			dosyaOkuyucu.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void WriteTable() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.println(grafTablosu[i][j]);
			}
		}

	}

	public Map<String, List<String>> getIsimSehirMap() {
		return isimSehirMap;
	}

	public Map<String, Integer> getSehirNumaraMap() {
		return sehirNumaraMap;
	}

	public Map<Integer, String> getNumberSehirMap() {
		return numaraSehirMap;
	}

	public Map<String, Float> getIsimHizMap() {
		return isimHizMap;
	}

	public int[][] getGraphTable() {
		return grafTablosu;
	}

}
