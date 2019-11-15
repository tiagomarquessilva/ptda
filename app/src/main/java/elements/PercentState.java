
package elements;

public class PercentState {

    private float state;
    
    public PercentState(float state) {
        
        if(isValidState(state)){
            this.state = state;
        }
        
    }
     
    public boolean isValidState(float state){
        if ( !(state < 0 || state > 100) ){
            
            return true;
            
        } else {
            
            return false;
            
        }
    }

    public float getState() {
        return state;
    }

    public void setState(float state) {
        this.state = state;
    }
    
}
