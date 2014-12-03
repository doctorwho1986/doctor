package spring.core.pracitce;

import java.util.EventListener;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class JavaEvent {
	
	@Test
	public void test(){
		DoorManager doorManager = new DoorManager();
		doorManager.add(new OpenDoorEventListener1());
		doorManager.add(new OpenDoorEventListener2());
		doorManager.fire("open");
		System.out.println("我进来了");
		doorManager.fire("close");
	}
	
	
	public static class DoorEvent extends EventObject{
		private static final long serialVersionUID = -1777014814744168277L;
		
		private String doorState = "";
		public DoorEvent(Object source,String doorState) {
			super(source);
			this.doorState = doorState;
		}
		public String getDoorState() {
			return doorState;
		}
		public void setDoorState(String doorState) {
			this.doorState = doorState;
		}
		
	}
	
	public interface DoorEventListener extends EventListener{
		public void doorEvent(DoorEvent doorEvent);
	}
	
	public static class OpenDoorEventListener1 implements DoorEventListener{

		@Override
		public void doorEvent(DoorEvent doorEvent) {
			if (doorEvent.getSource()!= null && doorEvent.getDoorState().equals("open")) {
				System.out.println("open door1");
			}else {
				System.out.println("close door1");
			}
			
		}
		
	}
	
	public static class OpenDoorEventListener2 implements DoorEventListener{

		@Override
		public void doorEvent(DoorEvent doorEvent) {
			if (doorEvent.getSource()!= null && doorEvent.getDoorState().equals("open")) {
				System.out.println("open door2,同时打开走廊的灯");
			}else {
				System.out.println("close door2，同时关闭走廊的灯");
			}
			
		}
		
	}
	
	public static class DoorManager{
		private List<DoorEventListener> list = new LinkedList<>();
		
		public void add(DoorEventListener doorEventListener){
			list.add(doorEventListener);
		}
		
		public void remove(DoorEventListener doorEventListener){
			list.remove(doorEventListener);
		}
		
		public void fire(String state){
			DoorEvent doorEvent = new DoorEvent(this, state);
			for (DoorEventListener doorEventListener : list) {
				doorEventListener.doorEvent(doorEvent);
			}
		}
	}
}
