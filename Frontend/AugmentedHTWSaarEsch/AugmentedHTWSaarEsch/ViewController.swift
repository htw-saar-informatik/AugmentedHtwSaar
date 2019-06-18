//
//  ViewController.swift
//  AugmentedHTWSaarEsch
//
//  Created by Christian Herz, Philipp Kügler on 13.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import UIKit
import ARKit
//import SceneKit

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
        //isnt doing anything in our case
        //configuartion.planeDetection = .horizontal
        configuartion.detectionImages = ARReferenceImage.referenceImages(inGroupNamed: "AR Resources", bundle: nil)
        ARView.session.run(configuartion)
        if keinBrettAngezeigt {
            viewFuerButtons.isHidden = true
            btnZeigeBtnView.isHidden = true
            btnVersteckeBtnView.isHidden = true
        }
    }

    
    override func viewDidAppear(_ animated: Bool) {
        //test()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        ARView.session.pause()
    }

    /*
     Funktion zum erkennen von gespeicherten Bildern. Diese Methode braucht mehr Speicher als das
     erkennen von QR Codes, ist aber leichter zu implementieren, da diese Funktion Teil der
     ARKITs ist
    */
    func renderer(_ renderer: SCNSceneRenderer, didUpdate node: SCNNode, for anchor: ARAnchor) {
        if true {
        
            if let imageAnchor = anchor as? ARImageAnchor {
                let name = imageAnchor.referenceImage.name!
                print("you found a \(name) image")
                
                let size = imageAnchor.referenceImage.physicalSize
                let videoNode = makeText(size: size)
                node.addChildNode(videoNode)
                node.opacity = 1
                node.scale = SCNVector3(0.02, 0.02, 0.02)
            }
        }
    }
    
    func makeVideo(size: CGSize) -> SCNNode{
        // 1
        guard let videoURL = Bundle.main.url(forResource: "slovenia",
                                             withExtension: "mp4") else {
                                                print("No Video found!")
                                                return SCNNode()
        }
        
        // 2
        let avPlayerItem = AVPlayerItem(url: videoURL)
        let avPlayer = AVPlayer(playerItem: avPlayerItem)
        avPlayer.play()
        
        // 3
        NotificationCenter.default.addObserver(
            forName: .AVPlayerItemDidPlayToEndTime,
            object: nil,
            queue: nil) { notification in
                avPlayer.seek(to: .zero)
                avPlayer.play()
        }
        
        // 4
        let avMaterial = SCNMaterial()
        avMaterial.diffuse.contents = avPlayer
        
        // 5
        let videoPlane = SCNPlane(width: size.width, height: size.height)
        videoPlane.materials = [avMaterial]
        
        // 6
        let videoNode = SCNNode(geometry: videoPlane)
        videoNode.eulerAngles.x = -.pi / 2
        return videoNode
    }
    
    func makeText(size: CGSize) -> SCNNode{
        let text = SCNText(string: "Hello", extrusionDepth: 0.9)
        text.materials.first?.diffuse.contents = UIColor.green
        let textNode = SCNNode(geometry: text)
        //textNode.position = SCNVector3(0.5,0.5,-1)
        textNode.eulerAngles.x = (.pi / 2) //Make the text stand up straight (90 Degrees
        textNode.eulerAngles.z = .pi // Rotate it by 180 degrees

        return textNode
    }
    
    func makePlane(size: CGSize) -> SCNNode{
        
        // 5
        let videoPlane = SCNPlane(width: size.width, height: size.height)
        videoPlane.materials.first?.diffuse.contents = UIColor.green
        
        // 6
        let videoNode = SCNNode(geometry: videoPlane)
        videoNode.eulerAngles.x = -.pi / 2
        return videoNode
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
        
        raum = Raum.init(raumnummer: 1000, verantwortlicher: user, raumartID: nil, raumartBezeichnung: "Spassraum", htwSeite: "www.google.com")
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

