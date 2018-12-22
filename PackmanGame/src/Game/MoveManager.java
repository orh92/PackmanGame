package Game;

import java.util.ArrayList;
import java.util.Iterator;

import GUI.MyFrame;
import GameObjects.Packman;

public class MoveManager extends Thread {	
	ArrayList<Packman> packArray;
	Iterator<Packman> packIter;
	Packman pack;
	double packSpeed;
	private MyFrame myframe;	 
	public static int counterPack=0;
	int packsNumber;
	/**
	 * manage the packmans moves by thread
	 * when each packman done his move he do counterPack++ and the manager can know the status of each packman
	 * @param myframe
	 */
	public MoveManager(MyFrame myframe ) {
		this.myframe=myframe;
		packsNumber=myframe.getAlgo().getPackArray().size();
		packArray=myframe.getAlgo().getPackArray();
		packIter=packArray.iterator();
		boolean flag=true;
			while(packIter.hasNext()) {
				pack=packIter.next();
				if(!flag) {
					if(pack.getSpeedWeight()>packSpeed) {
						packSpeed=pack.getSpeedWeight();
					}
				}
				else {packSpeed=pack.getSpeedWeight();
				flag=false;}
			}
	}
	public void run() {
		packIter=packArray.iterator();
		while(packIter.hasNext()) {
			pack=packIter.next();
			PackmanMove pm =new PackmanMove(pack,myframe);
			pm.start();
		}
		while(counterPack< packsNumber) {
			try {
				Thread.sleep((long) (400/packSpeed));//   : the fastest pack
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("all the packmans finished");
	}
}
