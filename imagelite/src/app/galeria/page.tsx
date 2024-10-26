
"use client";
import { Template, ImageCard } from '@/components'
import { useState } from 'react'

export default function GaleriaPage(){

    const image1 = 'https://i0.wp.com/picjumbo.com/wp-content/uploads/beautiful-spring-summer-day-in-green-nature-mountains-free-image.jpg?resize=350%2C200&ssl=1';
    const image2 = 'https://img.freepik.com/premium-photo/mountain-with-sun-shining-through-trees_931141-161.jpg';

    const [codImage, setCodImage] = useState<number>(2);
    const [urlImage, setUrlImage] = useState<string>(image2);
    

    function mudarImagem(){
        if(codImage == 1){
            setCodImage(2);
            setUrlImage(image2);
        }else{
            setCodImage(1);
            setUrlImage(image1);
        }
    }

    return(
       <Template>
            <button className="bg-gray-500" onClick={mudarImagem}>Clique para mudar</button>
            <section className='grid grid-cols-4 gap-8'>

                <ImageCard nome='natureza' tamanho='10MB' dataUpload='26/10/2024' src={urlImage}/>
                <ImageCard nome='natureza' tamanho='10MB' dataUpload='26/10/2024' src={urlImage}/>
                <ImageCard nome='natureza' tamanho='10MB' dataUpload='26/10/2024' src={urlImage}/>
               
            </section>
       </Template>
    )
}