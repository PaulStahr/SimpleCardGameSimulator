package gameObjects.instance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import gameObjects.instance.ObjectInstance.Relation;
import io.ObjectStateIO;
import util.data.IntegerArrayList;

public abstract class ObjectState implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6447814893037551696L;
	public int posX = 0;
	public int originalX = 0;
	public int posY = 0;
	public int originalY = 0;
	public int originalRotation = 0;
	public int rotation = 0;
	public int scale = 1;
	public int owner_id = -1;
	public int isSelected = -1;
	transient public boolean isActive = false;
	transient public long lastChange = System.nanoTime();
	public boolean inPrivateArea = false;

	/*stacking objects on top of each other*/
	public int aboveInstanceId = -1;
	public int belowInstanceId = -1;

	public int liesOnId = -1;
	public final IntegerArrayList aboveLyingObectIds = new IntegerArrayList();

	public int value = 0;
	public int sortValue = 0;
	public int drawValue = 0;
	public int rotationStep = -1;

	/*fix an object*/
	public boolean isFixed = false;
    public int boxId = -1;
    public boolean inBox = false;

    public int getRelatedId(Relation r) {return r == Relation.ABOVE ? aboveInstanceId : belowInstanceId;}

    @Override
	public int hashCode()
	{
		return posX ^ (posY << 16) ^ rotation ^ scale ^ owner_id;
	}

	public void set(ObjectState state) {
		this.posX = state.posX;
		this.originalX = state.originalX;
		this.posY = state.posY;
		this.originalY = state.originalY;
		this.originalRotation = state.originalRotation;
		this.rotation = state.rotation;
		this.scale = state.scale;
		this.owner_id = state.owner_id;
		this.isSelected = state.isSelected;
		this.inPrivateArea = state.inPrivateArea;
		this.aboveInstanceId = state.aboveInstanceId;
		this.belowInstanceId = state.belowInstanceId;
		this.liesOnId = state.liesOnId;
		this.aboveLyingObectIds.set(state.aboveLyingObectIds);
		this.value = state.value;
		this.sortValue = state.sortValue;
		this.rotationStep = state.rotationStep;
		this.boxId = state.boxId;
		this.inBox = state.inBox;
		this.isFixed = state.isFixed;
		this.lastChange = state.lastChange;
		this.isActive = state.isActive;
		this.drawValue = state.drawValue;
		this.lastChange = state.lastChange;
	}

	public abstract ObjectState copy();

	public double getRadiansRotation()
	{
		return Math.toRadians(rotation);
	}


	public void owner_select_reset(){
		owner_id = -1;
		isSelected = -1;
		isActive = false;
		inPrivateArea = false;
		lastChange = System.nanoTime();
	}

	public void box_reset(){
		posX = originalX;
		posY = originalY;
		rotation = originalRotation;
		owner_id = -1;
		isSelected = -1;
		isActive = false;
		inPrivateArea = false;
		aboveInstanceId = -1;
		belowInstanceId = -1;
		liesOnId = -1;
		aboveLyingObectIds.clear();
		lastChange = System.nanoTime();
	}


	public void reset()
	{
		posX = originalX;
		posY = originalY;
		rotation = originalRotation;
		scale = 1;
		owner_id = -1;
		isSelected = -1;
		isActive = false;
		inPrivateArea = false;
		aboveInstanceId = -1;
		belowInstanceId = -1;
		liesOnId = -1;
		aboveLyingObectIds.clear();
		drawValue = 1;
		inBox = false;
		isFixed = false;
		lastChange = System.nanoTime();
	}
	
	@Override
	public String toString()
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectStateIO.writeObjectStateToStreamXml(this, bos);
		} catch (IOException e) {
			return e.toString();
		}
		return bos.toString();
	}

	public String toOutputString(){
		StringBuilder outputString = new StringBuilder();

		outputString.append("");

		return outputString.toString();
	}
	
	@Override
    public boolean equals(Object other)
	{
	    if (other == this){return true;}
	    if (!(other instanceof ObjectState)){return false;}
	    ObjectState os = (ObjectState)other;
	    return this.posX == os.posX
				&& this.originalX == os.originalX
	            && this.posY == os.posY
				&& this.originalY == os.originalY
	            && this.rotation == os.rotation
	            && this.scale == os.scale
	            && this.owner_id == os.owner_id
	            && this.isSelected == os.isSelected
	            && this.inPrivateArea == os.inPrivateArea
	            && this.aboveInstanceId == os.aboveInstanceId
	            && this.belowInstanceId == os.belowInstanceId
				&& this.liesOnId == os.liesOnId
				&& this.aboveLyingObectIds.equals(os.aboveLyingObectIds)
	            && this.value == os.value
	            && this.sortValue == os.sortValue
	            && this.drawValue == os.drawValue
	            && this.rotationStep == os.rotationStep
				&& this.boxId == os.boxId
				&& this.inBox == os.inBox
				&& this.isFixed == os.isFixed;
	}
}
