//
//  ViewController.swift
//  AugmentedHTWSaarEsch
//
//  Created by Christian Herz, Philipp Kügler on 13.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import UIKit
import ARKit
import SceneKit

class ViewController: UIViewController, ARSCNViewDelegate, ARSessionDelegate {
    
    //Klassen Konstanten
    let ARScene = SCNScene()
    let brett = Brett()
    var user = User()
    let restSchnittstelleRaum = RestSchnittstelleRaum()
    var raum = Raum()
    
    //Klassen Varaiablen
    var keinBrettAngezeigt: Bool  = true
    @IBOutlet weak var ARView: ARSCNView! 
    @IBOutlet weak var btnVersteckeBtnView: UIButton!
    @IBOutlet weak var btnSprechzeiten: UIButton!
    @IBOutlet weak var viewFuerButtons: UIView!
    @IBOutlet weak var btnZeigeBtnView: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        ARView.delegate = self
        ARView.session.delegate = self
        ARView.autoenablesDefaultLighting = true
        ARView.scene = ARScene
        
        print("yah")
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        //May be changed to ARImagetrackingConfiguration
        //Could be changed dy
        let configuartion = ARWorldTrackingConfiguration()
        configuartion.detectionImages = ARReferenceImage.referenceImages(inGroupNamed: "AR Resources", bundle: nil)
        ARView.session.run(configuartion)
        if keinBrettAngezeigt {
            viewFuerButtons.isHidden = true
            btnZeigeBtnView.isHidden = true
            btnVersteckeBtnView.isHidden = true
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        test()
    }

    /*
     Funktion zum erkennen von gespeicherten Bildern. Diese Methode braucht mehr Speicher als das
     erkennen von QR Codes, ist aber leichter zu implementieren, da diese Funktion Teil der
     ARKITs ist
    */
    func renderer(_ renderer: SCNSceneRenderer, didUpdate node: SCNNode, for anchor: ARAnchor) {
        if keinBrettAngezeigt {
            
            
            guard let imageAnchor = anchor as? ARImageAnchor else {return}
            
            guard let currentFrame = ARView.session.currentFrame else {return}
            var translation = matrix_identity_float4x4
            translation.columns.3.z = -1 // entfernung von kamera
            
            if let imageName = imageAnchor.referenceImage.name {
                raum = restSchnittstelleRaum.getRaumInformation(raumnummer: Int(imageName)!)
                zeigeButtons()
                
                let alert = UIAlertController(title: imageName, message: "Zum Schließen lange gedrückt halten", preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
                present(alert, animated: true, completion: nil)
                
                
                keinBrettAngezeigt = false
                let planeNode = brett.erzeugeSchwarzesBrett(imageName: imageName, raum:raum)
                planeNode.simdTransform = matrix_multiply(currentFrame.camera.transform,translation)
                ARScene.rootNode.addChildNode(planeNode)
                
                
            }
        }
    }
    
    /*
     Test function, to get the board to show. Just to grasp understanding for last semesters work.
     */
    func test(){
        zeigeButtons()
        
        let alert = UIAlertController(title: "R808", message: "Zum Schließen lange gedrückt halten", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alert, animated: true, completion: nil)
        
        keinBrettAngezeigt = false
        
        raum = Raum.init(raumnummer: 1000, verantwortlicher: user, raumartID: 1, raumartBezeichnung: "Spassraum", htwSeite: "www.google.com")
        let planeNode = brett.erzeugeSchwarzesBrett(imageName: "Bla", raum:raum)
        guard let currentFrame = ARView.session.currentFrame else {return}
        var translation = matrix_identity_float4x4
        translation.columns.3.z = -1 // entfernung von kamera
        planeNode.simdTransform = matrix_multiply(currentFrame.camera.transform,translation)
        
        ARScene.rootNode.addChildNode(planeNode)
    }
    
    
    /*
     Hilfsfunktion zur Implementierung des Slide out Menüs
     */
    func zeigeButtons(){
        DispatchQueue.main.async(execute: { //Sorgt dafür, dass Code im Main Thread ausgeführt wird
            self.btnZeigeBtnView.isHidden = false
        })
    }
    
    /*
     Hilfsfunktion zur Implementierung des Slide out Menüs
     */
    @IBAction func zeigeBtnView(_ sender: Any) {
        self.viewFuerButtons.isHidden = false
        self.btnVersteckeBtnView.isHidden = false
        self.btnZeigeBtnView.isHidden = true
    }
    
    /*
     Hilfsfunktion zur Implementierung des Slide out Menüs
     */
    @IBAction func versteckeBtnView(_ sender: Any) {
        DispatchQueue.main.async(execute: { //Sorgt dafür, dass Code im Main Thread ausgeführt wird
            self.viewFuerButtons.isHidden = true
            self.btnVersteckeBtnView.isHidden = true
            self.btnZeigeBtnView.isHidden = false
        })
    }
    
    // Schliesst das Schwarze Brett
    @IBAction func schliessen(_ sender: Any) {
        keinBrettAngezeigt = true
        ARScene.rootNode.enumerateChildNodes{(planeNode, _) in
            planeNode.removeFromParentNode()
        }
        self.btnZeigeBtnView.isHidden = true
        if !self.viewFuerButtons.isHidden {
            self.viewFuerButtons.isHidden = true
            self.btnVersteckeBtnView.isHidden = true
        }
    }
    
    /*
     Überschreibt die FUnktion prepare und ermöglicht so das weiterleiten der informationen zu user und raum an den
     nächsten View Controller
     */
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if(segue.destination is KontaktViewController)
        {
            let vc = segue.destination as? KontaktViewController
            vc?.user = user
            vc?.raum = raum
        }
        if(segue.destination is SprechzeitenViewController)
        {
            let vc = segue.destination as? SprechzeitenViewController
            vc?.raum = raum
            vc?.user = user
        }
        if(segue.destination is SeiteViewController)
        {
            let vc = segue.destination as? SeiteViewController
            vc?.raum = raum
            vc?.user = user
        }
    }
    
    /*
     Funktion um andere Views zu schliessen (damit nicht der Speicher belastet wird)
     und zur Hauptansicht zurueckzukehren. So muss das Schwarze Brett nicht erneut
     aufgerufen werden
     */
    @IBAction func unwindZurueckZuHauptansicht(sender: UIStoryboardSegue){}
    
}

