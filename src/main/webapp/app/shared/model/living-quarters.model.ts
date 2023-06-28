export interface ILivingQuarters {
  id?: number;
  name?: string | null;
  city?: string | null;
  neighborhood?: string | null;
  description?: string | null;
  image?: string | null;
}

export class LivingQuarters implements ILivingQuarters {
  constructor(
    public id?: number,
    public name?: string | null,
    public city?: string | null,
    public neighborhood?: string | null,
    public description?: string | null,
    public image?: string | null
  ) {}
}
