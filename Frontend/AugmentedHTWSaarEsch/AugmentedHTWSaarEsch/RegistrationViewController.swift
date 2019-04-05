//
//  RegistrationViewController.swift
//  AugmentedHTWSaarEsch
//
//  Created by Christian Herz on 13.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import UIKit

class RegistrationViewController: UIViewController {

    
    
    @IBOutlet weak var usernameTextField: UITextField!
    
    @IBOutlet weak var emailTextField: UITextField!
    
    @IBOutlet weak var passwordTextField: UITextField!
    
    @IBOutlet weak var surnameTextField: UITextField!
    
    @IBOutlet weak var nameTextField: UITextField!
   
    var rolle = "Student"
    
    @IBOutlet weak var button: UIButton!
    @IBOutlet var roleButtons: [UIButton]!
    
    
    
    //create for dropDown
    var list = ["Admin","Professor", "Student"]
    
    var restSchnittstelleUser = RestSchnittStelleUser();
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    @IBAction func registerButtonTapped(_ sender: Any) {
        
        // Initialize Parameters for Registration
        let username = usernameTextField.text;
        let email = emailTextField.text;
        let password = passwordTextField.text;
        let surname = surnameTextField.text;
        let name = nameTextField.text;
        //let rolleDesUsers = rolle;
        
        //Check for empty fields
        if((username?.isEmpty)! || (email?.isEmpty)! || (password?.isEmpty)! || (surname?.isEmpty)! || (name?.isEmpty)! ){
            //Display alert message
            displayMyAlertMessage(userMessage: "All fields are required!");
            return;
        }
        var registrierung = restSchnittstelleUser.newUser(benutzerID: username!, email: email!, passwort: password!, name: name!, vorname: surname!, rollenbezeichnung:rolle)
        //Store Data
        
        displayMyAlertMessage(userMessage: registrierung);
        //Display alert message with confirmation
        
    }
    
    
    
    func displayMyAlertMessage(userMessage: String){
        
        let myAlert = UIAlertController(title: "Alert", message: userMessage, preferredStyle: UIAlertControllerStyle.alert);
        
        let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil);
        
        myAlert.addAction(okAction);
        
        self.present(myAlert, animated: true)
    }
    
    //Erstellen des Dropdownmenü
    @IBAction func handleSelection(_ sender: UIButton) {
        roleButtons.forEach{(button) in
            UIView.animate(withDuration: 0.3, animations: {
                button.isHidden = !button.isHidden
                self.view.layoutIfNeeded()
            })
        }
    }
    
    enum Roles: String {
        case admin = "Admin"
        case professor = "Professor"
        case student = "Student"
    }
    
        
    @IBAction func roleTapped(_ sender: UIButton) {
        guard let title = sender.currentTitle, let role = Roles(rawValue: title)
            else {
                return;
            }

        roleButtons.forEach{(button) in
            UIView.animate(withDuration: 0.3, animations: {
                button.isHidden = !button.isHidden
                self.view.layoutIfNeeded()
            })
        }
        
        switch role {
        case .admin:
            rolle = "Admin"
        case .professor:
            rolle = "Professor"
        case .student:
            rolle = "Student"
            break;
        default:
            print("student")
        }
        button.setTitle(rolle, for: .normal)
        
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
