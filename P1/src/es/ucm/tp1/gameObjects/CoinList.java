package es.ucm.tp1.gameObjects;

public class CoinList {
	
	private Coin coinList[] = new Coin[100];
	private int coinCounter; 
	
	public Coin[] getCoinList() {
		return coinList;
	}
	
	public int getCoinCounter() {
		return coinCounter;
	}
	
	public void addCoin(Coin coin) {  
		coinList[coinCounter] = coin;
		coinCounter++;
	}
	
	public boolean isThereCoin(int posX, int posY) {
		boolean weGotIt = false;
		int i = 0;
		
		while (!weGotIt && i < coinCounter) {
			if (coinList[i].getX() == posX && coinList[i].getY() == posY) {
				weGotIt = true;
			}
			else {
				i++;
			}
		}
		
		return weGotIt;
	}
	
	public void deleteCoin(Coin[] coinList) {
		
		Coin aux[] = new Coin[100];
		int j = 0;
		for(int i = 0; i < coinCounter;i++) {
			if(coinList[i].getAlive()) {
				aux[j] = coinList[i];
				j++;
			}				
		}	
		this.coinCounter--;
		this.coinList = aux;
	}
	 
	
}


