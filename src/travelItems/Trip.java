package travelItems;

import java.io.Serializable;
import java.util.ArrayList;

import jSONConverterProject.WeatherInfoObject;
import jSONConverterProject.WeatherObjectConverter;

public class Trip implements Serializable {

	private static final long serialVersionUID = 618353573734930660L;
	private WeatherInfoObject weatherInfoObject;
	private ArrayList<Item> items = new ArrayList<Item>();
	private PackingList packingList = new PackingList();
	private ArrayList<String> dates;

	double apiMinTemp;
	double apiMaxTemp;
	int apiWeatherCode;

	public Trip() {
		
		// add essential items
		items.add(packingList.getLipBalm());
		items.add(packingList.getConditioner());
		items.add(packingList.getDeodorant());
		items.add(packingList.getToothbrush());
		items.add(packingList.getToothpaste());
		items.add(packingList.getShampoo());
		items.add(packingList.getShaver());
		items.add(packingList.getSoap());

		// add quantity specific items
		items.add(packingList.getShoes());
		items.add(packingList.getSocks());
		items.add(packingList.getUnderwear());

		// fills staging list with non-essential items
		packingList.fillStagingList();

	}

	private void addNonEssentialItemsToPackingList() {
		for (int i = 0; i < packingList.stagingList.size(); i++) {
			packingList.stagingList.get(i).checkWeatherConditions(apiWeatherCode, apiMinTemp, apiMaxTemp);
			if (packingList.stagingList.get(i).included == true) {
				items.add(packingList.stagingList.get(i));
			}
		}
	}

	public WeatherInfoObject getWeatherInfoObject() {
		return weatherInfoObject;
	}

	public void setWeatherInfoObject(WeatherInfoObject weatherInfoObject) {
		this.weatherInfoObject = weatherInfoObject;
		
//		could the following be extracted?
		apiMinTemp = weatherInfoObject.list[0].temp.min;
		apiMaxTemp = weatherInfoObject.list[0].temp.max;
		apiWeatherCode = weatherInfoObject.list[0].weather[0].id;
		addNonEssentialItemsToPackingList();
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<String> getDates() {
		return dates;
	}

	public void setDates(ArrayList<String> dates) {
		this.dates = dates;
	}

	public void makePackingList() {

	}

	public double getApiMinTemp() {
		return apiMinTemp;
	}

	public void setApiMinTemp(double apiMinTemp) {
		this.apiMinTemp = apiMinTemp;
	}

	public double getApiMaxTemp() {
		return apiMaxTemp;
	}

	public void setApiMaxTemp(double apiMaxTemp) {
		this.apiMaxTemp = apiMaxTemp;
	}

	public int getApiWeatherCode() {
		return apiWeatherCode;
	}

	public void setApiWeatherCode(int apiWeatherCode) {
		this.apiWeatherCode = apiWeatherCode;
	}
}
