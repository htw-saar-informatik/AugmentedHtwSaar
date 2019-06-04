//
//  LoginViewController.swift
//  AugmentedHTWSaarEsch
//
//  Created by Christian Herz on 13.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController{
    
    //Username Feld
    @IBOutlet weak var usernameTextField: UITextField!
    //Passwort Feld
    @IBOutlet weak var passwordTextField: UITextField!
    
    var restSchnittstelleUser = RestSchnittStelleUser()
    var user = User()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    


    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //TODO fix dump user or delete it
    @IBAction func shortcutButtonPressed(_ sender: UIButton) {
        //use of let or var with value acessed type struct
        var dumpUser = User(benutzerID: 0000, benutzername: "dumpUser", passwort: "dumpUser", eMail: "dump@user.de", name: "dump", vorname: "dumpy", rollenID: 0, rollenBezeichnung: "Student")
        self.user = dumpUser
        
        performSegue(withIdentifier: "segueToScanner", sender: self)
    }
    @IBAction func loginButton(_ sender: Any) {
        
        
        if (usernameTextField.text == "" || passwordTextField.text == ""){
                displayAlertMessage(userMessage: "Sie haben nicht alle Felder ausgefüllt", additionalMessage: "Bitte füllen Sie alle Felder aus")
                return;
        } else {
            //Database Code
            user.benutzername = usernameTextField.text;
            user.passwort = passwordTextField.text;
            
            //ausführen der Schnittstellenfunktion zum einloggen eines Benutzers
            user = restSchnittstelleUser.login(user: user)
            if(user.benutzerID == 0)
            {
                displayAlertMessage(userMessage: "Login fehlgeschlagen", additionalMessage: "Benutzername und/oder Passwort falsch")
                return
            }
            performSegue(withIdentifier: "segueToScanner", sender: self)
          
        }

        
    }
    
    @IBAction func registerButton(_ sender: Any) {
    }
    
    func displayAlertMessage(userMessage: String, additionalMessage: String){
        let alert = UIAlertController(title: userMessage, message: additionalMessage, preferredStyle: .alert)
        
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        //alert.addAction(UIAlertAction(title: "No", style: .cancel, handler: nil))
        
        self.present(alert, animated: true)
    }
    
   
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if(segue.destination is ViewController)
        {
            let vc = segue.destination as? ViewController
            vc?.user = user
        }
    }
    
    /*Diese Funktionen dient blendet dazu die Tastatur auszublenden.
     Dies geschieht durch Drücken des Return-buttons
     */
    @IBAction func dismissKeyboard(_ sender: Any) {
        self.resignFirstResponder()
    }
    
    /*Diese Funktion dient dazu die Tastatur auszublenden.
     Dies geschieht, indem man eine beliebige Stelle außerhalb des Tastaturfeldes berührt
     */
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
