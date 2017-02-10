package lex_perm;

/**
 * Created by kelvinzero on 1/29/17.
 */
public class Logger {

    public int _phi;
    public int _cost;
    public int _lastCiHat;
    public int _thisCiHat;

    public Logger(){
        _phi = 0;
        _cost = 0;
        _lastCiHat = 0;
        _thisCiHat = 0;
    }
    public void nextLog(int phi, int cost){
        _phi = phi;
        _cost = cost;
        _lastCiHat = _thisCiHat;
        _thisCiHat = _cost + (_phi - _lastCiHat);
    }
}
