import { MidiaBase } from '../types';

interface MovieCardProps {
  midia: MidiaBase;
}

export const MovieCard = ({ midia }: MovieCardProps) => {
  const imageUrl = midia.posterPath 
    ? `https://image.tmdb.org/t/p/w500${midia.posterPath}`
    : 'https://via.placeholder.com/500x750?text=Sem+Imagem';

  return (
    <div className="group relative flex cursor-pointer flex-col overflow-hidden rounded-xl bg-zinc-900 transition-transform hover:scale-105 hover:shadow-xl">
      <img
        src={imageUrl}
        alt={midia.titulo}
        className="aspect-[2/3] w-full object-cover transition-opacity group-hover:opacity-80"
      />
      <div className="absolute inset-0 bg-gradient-to-t from-black/90 via-black/20 to-transparent opacity-0 transition-opacity group-hover:opacity-100">
        <div className="absolute bottom-0 flex w-full flex-col p-4">
          <h3 className="line-clamp-2 text-sm font-bold text-white md:text-base">
            {midia.titulo}
          </h3>
          <div className="mt-1 flex items-center gap-2 text-xs text-gray-300">
            <span className="font-semibold text-green-500">
              {midia.notaMedia.toFixed(1)} ★
            </span>
            <span>{new Date(midia.dataLancamento).getFullYear()}</span>
          </div>
        </div>
      </div>
    </div>
  );
};