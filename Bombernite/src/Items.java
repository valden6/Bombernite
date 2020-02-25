

public abstract class Items extends Entity{
	
	public Items(){
		super();
	}
	
	public Items(Coord coord, TypeEntity typeEntity){
		super(true, coord, typeEntity, true,true);

	}
	
	public void useItem(Player player){	
	}

}
