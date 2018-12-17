/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LugarEntregaComponentsPage, LugarEntregaDeleteDialog, LugarEntregaUpdatePage } from './lugar-entrega.page-object';

const expect = chai.expect;

describe('LugarEntrega e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let lugarEntregaUpdatePage: LugarEntregaUpdatePage;
    let lugarEntregaComponentsPage: LugarEntregaComponentsPage;
    let lugarEntregaDeleteDialog: LugarEntregaDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load LugarEntregas', async () => {
        await navBarPage.goToEntity('lugar-entrega');
        lugarEntregaComponentsPage = new LugarEntregaComponentsPage();
        expect(await lugarEntregaComponentsPage.getTitle()).to.eq('comprasGobUpApp.lugarEntrega.home.title');
    });

    it('should load create LugarEntrega page', async () => {
        await lugarEntregaComponentsPage.clickOnCreateButton();
        lugarEntregaUpdatePage = new LugarEntregaUpdatePage();
        expect(await lugarEntregaUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.lugarEntrega.home.createOrEditLabel');
        await lugarEntregaUpdatePage.cancel();
    });

    it('should create and save LugarEntregas', async () => {
        const nbButtonsBeforeCreate = await lugarEntregaComponentsPage.countDeleteButtons();

        await lugarEntregaComponentsPage.clickOnCreateButton();
        await promise.all([
            lugarEntregaUpdatePage.setDescripcionInput('descripcion'),
            lugarEntregaUpdatePage.setEstadoInput('estado'),
            lugarEntregaUpdatePage.setMunicipioInput('municipio'),
            lugarEntregaUpdatePage.setDireccionInput('direccion'),
            lugarEntregaUpdatePage.setLatitudInput('latitud'),
            lugarEntregaUpdatePage.setLongitudInput('longitud'),
            lugarEntregaUpdatePage.dependenciaSelectLastOption()
        ]);
        expect(await lugarEntregaUpdatePage.getDescripcionInput()).to.eq('descripcion');
        expect(await lugarEntregaUpdatePage.getEstadoInput()).to.eq('estado');
        expect(await lugarEntregaUpdatePage.getMunicipioInput()).to.eq('municipio');
        expect(await lugarEntregaUpdatePage.getDireccionInput()).to.eq('direccion');
        expect(await lugarEntregaUpdatePage.getLatitudInput()).to.eq('latitud');
        expect(await lugarEntregaUpdatePage.getLongitudInput()).to.eq('longitud');
        await lugarEntregaUpdatePage.save();
        expect(await lugarEntregaUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await lugarEntregaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last LugarEntrega', async () => {
        const nbButtonsBeforeDelete = await lugarEntregaComponentsPage.countDeleteButtons();
        await lugarEntregaComponentsPage.clickOnLastDeleteButton();

        lugarEntregaDeleteDialog = new LugarEntregaDeleteDialog();
        expect(await lugarEntregaDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.lugarEntrega.delete.question');
        await lugarEntregaDeleteDialog.clickOnConfirmButton();

        expect(await lugarEntregaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
